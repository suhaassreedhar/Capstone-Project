package com.suhaas.capstonestage2.injection;


import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.suhaas.capstonestage2.analytics.CrashAnalytics;
import com.suhaas.capstonestage2.analytics.UsageAnalytics;
import com.suhaas.capstonestage2.data.ConnectionProvider;
import com.suhaas.capstonestage2.data.DataPersister;
import com.suhaas.capstonestage2.data.Provider;
import com.suhaas.capstonestage2.invite.AppInviter;

public class DefaultDependenciesFactory implements DependenciesFactory {

    private final Context context;

    public DefaultDependenciesFactory(Context context) {
        this.context = context;
    }

    @Override
    public DataPersister createDatabasePersister() {
        return new DataPersister(context.getContentResolver());
    }

    @Override
    public Provider createDataRepository(DataPersister dataPersister) {
        return new Provider(dataPersister);
    }

    @Override
    public CrashAnalytics createCrashAnalytics() {
        return new CrashAnalytics() {
            @Override
            public void logSomethingWentWrong(String errorMessage) {
                
            }

            @Override
            public void logSomethingWentWrong(String errorMessage, Throwable throwable) {

            }
        };
    }

    @Override
    public ConnectionProvider createConnection() {
        return new ConnectionProvider();
    }

    @Override
    public UsageAnalytics createUsageAnalytics() {
        return new UsageAnalytics();
    }

    @Override
    public AppInviter createAppInviter() {
        return new AppInviter();
    }

}
