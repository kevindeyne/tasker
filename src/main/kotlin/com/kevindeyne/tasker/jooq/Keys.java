/*
 * This file is generated by jOOQ.
*/
package com.kevindeyne.tasker.jooq;


import com.kevindeyne.tasker.jooq.tables.Issue;
import com.kevindeyne.tasker.jooq.tables.SchemaVersion;
import com.kevindeyne.tasker.jooq.tables.User;
import com.kevindeyne.tasker.jooq.tables.records.IssueRecord;
import com.kevindeyne.tasker.jooq.tables.records.SchemaVersionRecord;
import com.kevindeyne.tasker.jooq.tables.records.UserRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>taskr</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<IssueRecord, Long> IDENTITY_ISSUE = Identities0.IDENTITY_ISSUE;
    public static final Identity<UserRecord, Long> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<IssueRecord> KEY_ISSUE_PRIMARY = UniqueKeys0.KEY_ISSUE_PRIMARY;
    public static final UniqueKey<SchemaVersionRecord> KEY_SCHEMA_VERSION_PRIMARY = UniqueKeys0.KEY_SCHEMA_VERSION_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<IssueRecord, Long> IDENTITY_ISSUE = createIdentity(Issue.ISSUE, Issue.ISSUE.ID);
        public static Identity<UserRecord, Long> IDENTITY_USER = createIdentity(User.USER, User.USER.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<IssueRecord> KEY_ISSUE_PRIMARY = createUniqueKey(Issue.ISSUE, "KEY_issue_PRIMARY", Issue.ISSUE.ID);
        public static final UniqueKey<SchemaVersionRecord> KEY_SCHEMA_VERSION_PRIMARY = createUniqueKey(SchemaVersion.SCHEMA_VERSION, "KEY_schema_version_PRIMARY", SchemaVersion.SCHEMA_VERSION.INSTALLED_RANK);
        public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = createUniqueKey(User.USER, "KEY_user_PRIMARY", User.USER.ID);
    }
}
