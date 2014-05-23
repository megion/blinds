package com.megion.site.blinds.setup;

import info.magnolia.cms.security.Permission;
import info.magnolia.cms.security.Role;
import info.magnolia.cms.security.RoleManager;
import info.magnolia.cms.security.Security;
import info.magnolia.cms.security.User;
import info.magnolia.cms.security.UserManager;
import info.magnolia.module.DefaultModuleVersionHandler;
import info.magnolia.module.InstallContext;
import info.magnolia.module.delta.AbstractTask;
import info.magnolia.module.delta.DeltaBuilder;
import info.magnolia.module.delta.ModuleDependencyBootstrapTask;
import info.magnolia.module.delta.Task;
import info.magnolia.module.delta.TaskExecutionException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * VersionHandler for the module.
 */
public class MegionSiteVersionHandler extends DefaultModuleVersionHandler {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MegionSiteVersionHandler.class);

	final Task grantReadPermissionToAnonymousUser = new AbstractTask(
			"Anonymous permissions for megion",
			"Grants the anonymous user the read permission to the megion.") {
		@Override
		public void execute(InstallContext installContext)
				throws TaskExecutionException {

			RoleManager roleManager = Security.getRoleManager();
			Role anonymous = roleManager.getRole("anonymous");

			roleManager.addPermission(anonymous, "config", "/modules/megion-site-blinds/config",
					Permission.READ);
			roleManager.addPermission(anonymous, "config", "/modules/megion-site-blinds/config/*",
					Permission.READ);
		}
	};

	final Task addReadRoleToAnonymousUser = new AbstractTask(
			"Anonymous permissions for megion",
			"Grants the anonymous user the read permission to the megion.") {
		@Override
		public void execute(InstallContext installContext)
				throws TaskExecutionException {
			UserManager userManager = Security.getUserManager();
			User anonymous = userManager.getUser("anonymous");
			userManager.addRole(anonymous, "megion-base");
		}
	};

	public MegionSiteVersionHandler() {
		register(DeltaBuilder.update("1.0.2", ""));
	}

	@Override
	protected List<Task> getBasicInstallTasks(InstallContext installContext) {
		final List<Task> installTasks = new ArrayList<Task>();

		// make sure we register the type before doing anything else
		installTasks.addAll(super.getBasicInstallTasks(installContext));
		return installTasks;
	}

	@Override
    protected List<Task> getExtraInstallTasks(InstallContext installContext) {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new ModuleDependencyBootstrapTask("mail"));
        tasks.add(addReadRoleToAnonymousUser);
        return tasks;
    }
}
