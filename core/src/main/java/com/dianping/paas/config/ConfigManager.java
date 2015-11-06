package com.dianping.paas.config;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public interface ConfigManager {

    List<String> getNatsServerList();

    List<String> getRoleList();

    boolean isLocalMode();

    boolean isVerbose();

    String getInstanceProvider();

    String getDockerIp();

    int getDockerPort();

    File getBaseWebappDir();

    String getDockerImage(String imageId);

    String getInnerWarRoot(String imageId);

    int getStartHostPort();

    String getConsoleBaseUrl(String ip);

    String getDockBaseDir();

    String getDockFileSystem();

    int getHealthCheckTimeout();

    String getSlbDomain();

    String getRouterProvider();

    int getInstanceCreateTimeout();

    String getAgentIp();

    String getAppConfigFile();

    String getDownloadPath();

    String getUploadPath();

    int getInstanceShutdownTimeout();

    String getAppEnvContent();

    String getEndpointIp();

    String getLocalRepositoryBaseDir();

    int getRouterRequestTimeout();

    int getSocketConnectTimeout();

    int getMaximumInstanceCountPerApp();

    int getAppIdMaxLength();

    Pattern getAppIdPattern();

    String getInnerSupervisorDir(String imageId);

    String getOuterSupervisorDir(String instanceRoot);

    String getSupervisorContent(String imageId);

    String getInnerRootXmlFile(String imageId);

    String getOuterRootXmlFile(String instanceRoot);

    String getRootXmlContent(String imageId, boolean withKernel);

    int getInstanceCrashRetryTimes();

    int getInstanceCrashRetryPeriod();

    String getRepositoryDownloadUrl(String token);

    String getRepositoryUploadUrl(String token);

    int getAgentMonitorPeriod();

    int getAgentReloadFromDBPeriod();

    int getAgentHeartbeatTimeout();

    int getAgentHeartbeatTimeoutCount();

    int getCpuShareGroupMaxInstance();

    float getCpuShareGroupPower();

    int getInstanceUpgradeTimeout();

    int getAgentFindTimeout();

    int getJvmSelfMemoryMega();

    int getInstanceRemoveTimeout();

    int getMaxPermSizeMega();

    int getMaxStackSizeMega();

    String getInnerKernelDir(String imageId);

    String getOuterLdapDir(String instanceRoot);

    String getPhoenixKernelGitUr();

    String getPhoenixKernelDefaultVer();

    int getWarmUpCheckTimeout();

    String getCatServerIps();

    int getPigeonApiTimeout();

    int getKernelUpgradeTimeout();

    long getBlackholeNotifyPeriod();

    String getBlackholeLoginPath();

    int getBlackholeLoginTimeout();

    String getBlackholeLogoutPath();

    int getBlackholeLogoutTimeout();

    String getBlackholeNotifyHost();

    String getLigerProperty(String key);

    String getLogscanApiSecret();

    String getLogscanApiKey();

    String getLogscanUpdateUrlPattern();

    String getLogscanPrefixPattern();

    long getLogscanNotifyPeriod();

    String getCmdbUpdateUrlPattern();

    String getCmdbUpdateEnv();

    boolean shouldNotifyCmdb();

    boolean shouldNotifyBlackhole();

    boolean shouldNotifyLogscan();

    int getCpuGroupCapacity();

    String getOuterNginxDir(String instanceRoot);

    int getWarmUpCheckRetryTimes();

    int getWarmUpCheckInterval();

    String getOuterPuppetDir(String instanceRoot);

    int getPuppetMasterRequestTimeout();

    String getPuppetMasterUrlPattern();

    String getPuppetMasterProvider();

    int getRolloutRequestTimeout();

    String getRolloutUrlPattern();

    String getRolloutProvider();

    boolean shouldNotifySlb();

    boolean shouldWarmup();

    long getSysopNotifyPeriod();

    String getSysopUrlPattern();

    boolean shouldNotifySysop();

    long removeInstanceInterval();

    String getFtpIp();

    String getFtpUserName();

    String getFtpPassword();

    String getUploadDir();

    /**
     * 表示备份的时候，该删除多少天前的war包 若返回180，表示备份的时候删除180天前的war包
     */
    int getDaysAgoOfDeleteWhenBackup();

    String getPaasApiHost();

    /**
     * 表示删除war包的时候应该保留多少war包，删除的时候按照日期排序，将老的war包删掉
     */
    int getCountOfReserveWhenDelete();

    long getInstanceMonitorPeriod();

    long getDelayStartTime();

    long getAgentDeadDetectTime();

    long getCrashInstanceMonitorPeriod();

    long getAlarmTaskStartTime();

    long getAlarmPeriod();

    List<String> getIpWhiteList();
}
