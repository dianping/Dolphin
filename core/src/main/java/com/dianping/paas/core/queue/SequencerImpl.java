package com.dianping.paas.core.queue;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/14.
 */
@Component
public class SequencerImpl<T> implements Sequencer<T> {

    private BlockingQueue<SequencedObject<T>> readyTasks = new LinkedBlockingQueue<SequencedObject<T>>();

    private ConcurrentMap<String, BlockingQueue<SequencedObject<T>>> waitingTasks = new ConcurrentHashMap<String, BlockingQueue<SequencedObject<T>>>();

    private Set<String> toBeDoneTaskIds = new HashSet<String>();

    @Override
    public synchronized void offer(String id, T obj) {
        DefaultTask task = new DefaultTask(id, obj);

        if (hasUndoneTask(id)) {
            offerToWaitingQueue(id, task);
        } else {
            recordNewUndoneTask(id);
            offerToReadyQueue(task);
        }
    }

    @Override
    public SequencedObject<T> take() throws InterruptedException {
        return readyTasks.take();
    }

    private void recordNewUndoneTask(String id) {
        toBeDoneTaskIds.add(id);
    }

    private void offerToReadyQueue(DefaultTask task) {
        readyTasks.offer(task);
    }

    private void offerToWaitingQueue(String id, DefaultTask task) {
        BlockingQueue<SequencedObject<T>> queue = waitingTasks.get(id);
        if (queue == null) {
            queue = new LinkedBlockingQueue<SequencedObject<T>>();
            waitingTasks.put(id, queue);
        }
        queue.offer(task);
    }

    private boolean hasUndoneTask(String id) {
        return toBeDoneTaskIds.contains(id);
    }

    public synchronized void done(String id) {
        toBeDoneTaskIds.remove(id);

        BlockingQueue<SequencedObject<T>> queue = waitingTasks.get(id);
        if (queue != null) {
            SequencedObject<T> task = queue.poll();
            if (task != null) {
                readyTasks.offer(task);
            }
        }
    }

    private class DefaultTask implements SequencedObject<T> {

        private String m_id;

        private T m_payload;

        public DefaultTask(String id, T payload) {
            m_id = id;
            m_payload = payload;
        }

        public T getPayload() {
            return m_payload;
        }

        public void done() {
            SequencerImpl.this.done(m_id);
        }

        public String getId() {
            return m_id;
        }

    }

}
