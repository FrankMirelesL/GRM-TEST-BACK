package com.clinica.sistemas.reina.madre.config;

import org.hibernate.community.dialect.identity.SQLiteIdentityColumnSupport;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

public class SQLiteDialect extends Dialect {
    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }
}
