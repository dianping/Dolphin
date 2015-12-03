package com.dianping.paas.core.message.nats.subscribe;

/**
 * Created by yuchao on 12/1/15.
 */
public interface Subject {

    /**
     * ====================== Other ==================
     */
    String UPGRADE_PHOENIX_KERNEL_REQUEST = "upgrade_phoenix_kernel_request";
    String PHOENIX_KERNEL_UPGRADED = "phoenix_kernel_upgraded";

    /**
     * ====================== Health Manager ===================
     */
    interface HealthManager {
        String HEALTH_MANAGER_START = "health_manager_start";

        String HEALTH_MANAGER_APP_STATUS_REQUEST = "health_manager_app_status_request";

        String WARM_UP_URL_UPDATED = "warm_up_url_updated";
    }

    /**
     * ====================== Route ==================
     */

    interface Route {
        String ADD_ROUTE_REQUEST = "add_route_request";

        String ADD_ROUTE_RESPONSE = "add_route_response";

        String ADD_ROUTE_SITE_REQUEST = "add_route_site_request";

        String ADD_ROUTE_SITE_RESPONSE = "add_route_site_response";

        String REMOVE_ROUTE_REQUEST = "remove_route_request";

        String REMOVE_ROUTE_RESPONSE = "remove_route_response";

        String ENABLE_ROUTE_REQUEST = "enable_route_request";

        String ENABLE_ROUTE_RESPONSE = "enable_route_response";

        String DISABLE_ROUTE_REQUEST = "disable_route_request";

        String DISABLE_ROUTE_RESPONSE = "disable_route_response";

    }

    /**
     * ====================== Resource Manager ===================
     */
    interface ResourceManager {
        String RESOURCE_MANAGER_START = "resource_manager_start";
        String RESOURCE_MANAGER_MACHINE_STATUS_REQUEST = "resource_manager_machine_status_request";

    }

    /**
     * ====================== Agent ==================
     */

    interface Agent {
        String FIND_AGENT_REQUEST = "find_agent_request";
        String FIND_AGENT_RESPONSE = "find_agent_response";
        String AGENT_HEARTBEAT = "agent_heartbeat";
        String AGENT_HEARTBEAT_TIMEOUT_RESPONSE = "agent_heartbeat_timeout_response";
    }

    /**
     * ====================== Repository =============
     */
    interface Repository {
        String BACKUP_REPOSITORY_REQUEST = "backup_repository_request";

        String DELETE_WAR_REPOSITORY_REQUEST = "delete_war_repository_request";

        String ALLOCATE_WEB_PACKAGE_REQUEST = "allocate_repository_request";

        String ALLOCATE_REPOSITORY_RESPONSE = "allocate_repository_response";

    }

    /**
     * ====================== Instance ===============
     */
    interface Instance {
        String NEW_AND_DEPLOY = "instance_new_and_deploy";

        String STATUS_REPORT = "instance_status_report";

        String NEW_AND_DEPLOY_WAR_REQUEST = "instance_new_and_deploy_war_request";

        String NEW_AND_DEPLOY_WAR_RESPONSE = "instance_new_and_deploy_war_response";

        String SHUTDOWN_REQUEST = "instance_shutdown_request";

        String REMOVE_REQUEST = "instance_remove_request";

        String DEPLOYED = "instance_deployed";

        String SHUTDOWN = "instance_shutdown";

        String REMOVED = "instance_removed";

        String UPGRADE_REQUEST = "instance_upgrade_request";

        String UPGRADED = "instance_upgraded";

        String CRASH = "instance_crash";

        String STARTED = "instance_started";

        String RESTART_REQUEST = "instance_restart_request";

        String CRASH_REPORT = "instance_crash_report";

        String START_REQUEST = "instance_start_request";

        String RESTARTED = "instance_restarted";

        String BLACKHOLE_REMOVED = "instance_blackhole_removed";

        String UNAVAILABLE = "instance_unavailable";

        String PULL_IMAGE_AND_RUN = "pull_image_and_run";
    }

}
