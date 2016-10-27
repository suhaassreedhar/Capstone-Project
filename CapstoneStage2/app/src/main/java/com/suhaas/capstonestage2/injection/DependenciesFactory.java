package com.suhaas.capstonestage2.injection;


import com.suhaas.capstonestage2.analytics.CrashAnalytics;
import com.suhaas.capstonestage2.analytics.UsageAnalytics;
import com.suhaas.capstonestage2.data.ConnectionProvider;
import com.suhaas.capstonestage2.data.DataPersister;
import com.suhaas.capstonestage2.data.Provider;
import com.suhaas.capstonestage2.invite.AppInviter;

public interface DependenciesFactory {

    DataPersister createDatabasePersister();

    Provider createDataRepository(DataPersister dataPersister);

    CrashAnalytics createCrashAnalytics();

    ConnectionProvider createConnection();

    UsageAnalytics createUsageAnalytics();

    AppInviter createAppInviter();

}
