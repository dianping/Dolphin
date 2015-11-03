package com.dianping.paas.config;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * TODO 默认为读配置文件
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/2.
 */
public class DefaultConfigManager implements ConfigManager {
    public List<String> getNatsServerList() {
        return null;
    }

    public List<String> getRoleList() {
        return null;
    }

    public boolean isLocalMode() {
        return false;
    }

    public boolean isVerbose() {
        return false;
    }

    public String getInstanceProvider() {
        return null;
    }

    public String getDockerIp() {
        return "0.0.0.0";
    }

    public int getDockerPort() {
        return 0;
    }

    public File getBaseWebappDir() {
        return null;
    }

    public String getDockerImage(String imageId) {
        return null;
    }

    public String getInnerWarRoot(String imageId) {
        return null;
    }

    public int getStartHostPort() {
        return 0;
    }

    public String getConsoleBaseUrl(String ip) {
        return null;
    }

    public String getDockBaseDir() {
        return null;
    }

    public String getDockFileSystem() {
        return null;
    }

    public int getHealthCheckTimeout() {
        return 0;
    }

    public String getSlbDomain() {
        return null;
    }

    public String getRouterProvider() {
        return null;
    }

    public int getInstanceCreateTimeout() {
        return 0;
    }

    public String getAgentIp() {
        return null;
    }

    public String getAppConfigFile() {
        return null;
    }

    public String getDownloadPath() {
        return null;
    }

    public String getUploadPath() {
        return null;
    }

    public int getInstanceShutdownTimeout() {
        return 0;
    }

    public String getAppEnvContent() {
        return null;
    }

    public String getEndpointIp() {
        return null;
    }

    public String getLocalRepositoryBaseDir() {
        return null;
    }

    public int getRouterRequestTimeout() {
        return 0;
    }

    public int getSocketConnectTimeout() {
        return 0;
    }

    public int getMaximumInstanceCountPerApp() {
        return 0;
    }

    public int getAppIdMaxLength() {
        return 0;
    }

    public Pattern getAppIdPattern() {
        return null;
    }

    public String getInnerSupervisorDir(String imageId) {
        return null;
    }

    public String getOuterSupervisorDir(String instanceRoot) {
        return null;
    }

    public String getSupervisorContent(String imageId) {
        return null;
    }

    public String getInnerRootXmlFile(String imageId) {
        return null;
    }

    public String getOuterRootXmlFile(String instanceRoot) {
        return null;
    }

    public String getRootXmlContent(String imageId, boolean withKernel) {
        return null;
    }

    public int getInstanceCrashRetryTimes() {
        return 0;
    }

    public int getInstanceCrashRetryPeriod() {
        return 0;
    }

    public String getRepositoryDownloadUrl(String token) {
        return null;
    }

    public String getRepositoryUploadUrl(String token) {
        return null;
    }

    public int getAgentMonitorPeriod() {
        return 0;
    }

    public int getAgentReloadFromDBPeriod() {
        return 0;
    }

    public int getAgentHeartbeatTimeout() {
        return 0;
    }

    public int getAgentHeartbeatTimeoutCount() {
        return 0;
    }

    public int getCpuShareGroupMaxInstance() {
        return 0;
    }

    public float getCpuShareGroupPower() {
        return 0;
    }

    public int getInstanceUpgradeTimeout() {
        return 0;
    }

    public int getAgentFindTimeout() {
        return 0;
    }

    public int getJvmSelfMemoryMega() {
        return 0;
    }

    public int getInstanceRemoveTimeout() {
        return 0;
    }

    public int getMaxPermSizeMega() {
        return 0;
    }

    public int getMaxStackSizeMega() {
        return 0;
    }

    public String getInnerKernelDir(String imageId) {
        return null;
    }

    public String getOuterLdapDir(String instanceRoot) {
        return null;
    }

    public String getPhoenixKernelGitUr() {
        return null;
    }

    public String getPhoenixKernelDefaultVer() {
        return null;
    }

    public int getWarmUpCheckTimeout() {
        return 0;
    }

    public String getCatServerIps() {
        return null;
    }

    public int getPigeonApiTimeout() {
        return 0;
    }

    public int getKernelUpgradeTimeout() {
        return 0;
    }

    public long getBlackholeNotifyPeriod() {
        return 0;
    }

    public String getBlackholeLoginPath() {
        return null;
    }

    public int getBlackholeLoginTimeout() {
        return 0;
    }

    public String getBlackholeLogoutPath() {
        return null;
    }

    public int getBlackholeLogoutTimeout() {
        return 0;
    }

    public String getBlackholeNotifyHost() {
        return null;
    }

    public String getLigerProperty(String key) {
        return null;
    }

    public String getLogscanApiSecret() {
        return null;
    }

    public String getLogscanApiKey() {
        return null;
    }

    public String getLogscanUpdateUrlPattern() {
        return null;
    }

    public String getLogscanPrefixPattern() {
        return null;
    }

    public long getLogscanNotifyPeriod() {
        return 0;
    }

    public String getCmdbUpdateUrlPattern() {
        return null;
    }

    public String getCmdbUpdateEnv() {
        return null;
    }

    public boolean shouldNotifyCmdb() {
        return false;
    }

    public boolean shouldNotifyBlackhole() {
        return false;
    }

    public boolean shouldNotifyLogscan() {
        return false;
    }

    public int getCpuGroupCapacity() {
        return 0;
    }

    public String getOuterNginxDir(String instanceRoot) {
        return null;
    }

    public int getWarmUpCheckRetryTimes() {
        return 0;
    }

    public int getWarmUpCheckInterval() {
        return 0;
    }

    public String getOuterPuppetDir(String instanceRoot) {
        return null;
    }

    public int getPuppetMasterRequestTimeout() {
        return 0;
    }

    public String getPuppetMasterUrlPattern() {
        return null;
    }

    public String getPuppetMasterProvider() {
        return null;
    }

    public int getRolloutRequestTimeout() {
        return 0;
    }

    public String getRolloutUrlPattern() {
        return null;
    }

    public String getRolloutProvider() {
        return null;
    }

    public boolean shouldNotifySlb() {
        return false;
    }

    public boolean shouldWarmup() {
        return false;
    }

    public long getSysopNotifyPeriod() {
        return 0;
    }

    public String getSysopUrlPattern() {
        return null;
    }

    public boolean shouldNotifySysop() {
        return false;
    }

    public long removeInstanceInterval() {
        return 0;
    }

    public String getFtpIp() {
        return null;
    }

    public String getFtpUserName() {
        return null;
    }

    public String getFtpPassword() {
        return null;
    }

    public String getUploadDir() {
        return null;
    }

    /**
     * 表示备份的时候，该删除多少天前的war包 若返回180，表示备份的时候删除180天前的war包
     */
    public int getDaysAgoOfDeleteWhenBackup() {
        return 0;
    }

    public String getPaasApiHost() {
        return null;
    }

    /**
     * 表示删除war包的时候应该保留多少war包，删除的时候按照日期排序，将老的war包删掉
     */
    public int getCountOfReserveWhenDelete() {
        return 0;
    }

    public long getInstanceMonitorPeriod() {
        return 0;
    }

    public long getDelayStartTime() {
        return 0;
    }

    public long getAgentDeadDetectTime() {
        return 0;
    }

    public long getCrashInstanceMonitorPeriod() {
        return 0;
    }

    public long getAlarmTaskStartTime() {
        return 0;
    }

    public long getAlarmPeriod() {
        return 0;
    }
}
