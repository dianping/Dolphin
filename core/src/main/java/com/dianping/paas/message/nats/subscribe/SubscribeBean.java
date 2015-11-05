/*
 *   Copyright (c) 2012 Mike Heath.  All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package com.dianping.paas.message.nats.subscribe;

import com.dianping.paas.message.nats.PaasSubject;
import nats.client.Message;
import nats.client.spring.Subscribe;
import org.springframework.stereotype.Component;

/**
 * @author Mike Heath
 */
@Component
public class SubscribeBean {

    /**
     * This subscribe method is broken because it does not have a nats.client.Message parameter.
     */
    @Subscribe(PaasSubject.NATS_SUBJECT)
    public void subscribe(Message message) {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
