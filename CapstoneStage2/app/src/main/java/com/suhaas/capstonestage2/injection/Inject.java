package com.suhaas.capstonestage2.injection;


import com.novoda.notils.exception.DeveloperError;
import com.suhaas.capstonestage2.analytics.CrashAnalytics;
import com.suhaas.capstonestage2.analytics.UsageAnalytics;
import com.suhaas.capstonestage2.data.ConnectionProvider;
import com.suhaas.capstonestage2.data.DataPersister;
import com.suhaas.capstonestage2.data.Provider;
import com.suhaas.capstonestage2.invite.AppInviter;

public class Inject {

    private static Inject INSTANCE;
    private final Provider provider;
    private final CrashAnalytics crashAnalytics;
    private final DataPersister persister;
    private final ConnectionProvider connectionProvider;
    private final UsageAnalytics analyticsTracker;
    private final AppInviter appInviter;

    private Inject(Provider provider, CrashAnalytics crashAnalytics, DataPersister persister, ConnectionProvider connectionProvider, UsageAnalytics analyticsTracker, AppInviter appInviter) {
        this.provider = provider;
        this.crashAnalytics = crashAnalytics;
        this.persister = persister;
        this.connectionProvider = connectionProvider;
        this.analyticsTracker = analyticsTracker;
        this.appInviter = appInviter;
    }

    public static void using(DependenciesFactory factory) {
        DataPersister dataPersister = factory.createDatabasePersister();
        Provider provider = factory.createDataRepository(dataPersister);
        CrashAnalytics crashAnalytics = factory.createCrashAnalytics();
        ConnectionProvider connectionProvider = factory.createConnection();
        UsageAnalytics analyticsTracker = factory.createUsageAnalytics();
        AppInviter appInviter = factory.createAppInviter();
        INSTANCE = new Inject(provider, crashAnalytics, dataPersister, connectionProvider, analyticsTracker, appInviter);
    }

    private static Inject instance() {
        if (INSTANCE == null) {
            throw new DeveloperError("You need to setup Inject to use a valid DependenciesFactory");
        }
        return INSTANCE;
    }

    public static Provider provider() {
        return instance().provider;
    }

    public static CrashAnalytics crashAnalytics() {
        return instance().crashAnalytics;
    }

    public static DataPersister dataPersister() {
        return instance().persister;
    }

    public static ConnectionProvider connectionProvider() {
        return instance().connectionProvider;
    }

    public static UsageAnalytics usageAnalytics() {
        return instance().analyticsTracker;
    }

    public static AppInviter appInviter() {
        return instance().appInviter;
    }

}

