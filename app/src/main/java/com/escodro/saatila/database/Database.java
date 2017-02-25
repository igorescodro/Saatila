package com.escodro.saatila.database;

/**
 * Created by IgorEscodro on 25/02/17.
 */

public abstract class Database {

    private DatabaseRealm mRealmDb;

    public Database(DatabaseRealm realmDb) {
        mRealmDb = realmDb;
    }

    public void close() {
        mRealmDb.close();
    }
}
