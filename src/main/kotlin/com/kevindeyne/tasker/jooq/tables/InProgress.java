/*
 * This file is generated by jOOQ.
*/
package com.kevindeyne.tasker.jooq.tables;


import com.kevindeyne.tasker.jooq.Indexes;
import com.kevindeyne.tasker.jooq.Keys;
import com.kevindeyne.tasker.jooq.Taskr;
import com.kevindeyne.tasker.jooq.tables.records.InProgressRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InProgress extends TableImpl<InProgressRecord> {

    private static final long serialVersionUID = 2021841648;

    /**
     * The reference instance of <code>taskr.in_progress</code>
     */
    public static final InProgress IN_PROGRESS = new InProgress();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InProgressRecord> getRecordType() {
        return InProgressRecord.class;
    }

    /**
     * The column <code>taskr.in_progress.id</code>.
     */
    public final TableField<InProgressRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>taskr.in_progress.issue_id</code>.
     */
    public final TableField<InProgressRecord, Long> ISSUE_ID = createField("issue_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>taskr.in_progress.user_id</code>.
     */
    public final TableField<InProgressRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>taskr.in_progress</code> table reference
     */
    public InProgress() {
        this(DSL.name("in_progress"), null);
    }

    /**
     * Create an aliased <code>taskr.in_progress</code> table reference
     */
    public InProgress(String alias) {
        this(DSL.name(alias), IN_PROGRESS);
    }

    /**
     * Create an aliased <code>taskr.in_progress</code> table reference
     */
    public InProgress(Name alias) {
        this(alias, IN_PROGRESS);
    }

    private InProgress(Name alias, Table<InProgressRecord> aliased) {
        this(alias, aliased, null);
    }

    private InProgress(Name alias, Table<InProgressRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Taskr.TASKR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.IN_PROGRESS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<InProgressRecord, Long> getIdentity() {
        return Keys.IDENTITY_IN_PROGRESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<InProgressRecord> getPrimaryKey() {
        return Keys.KEY_IN_PROGRESS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<InProgressRecord>> getKeys() {
        return Arrays.<UniqueKey<InProgressRecord>>asList(Keys.KEY_IN_PROGRESS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InProgress as(String alias) {
        return new InProgress(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InProgress as(Name alias) {
        return new InProgress(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public InProgress rename(String name) {
        return new InProgress(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public InProgress rename(Name name) {
        return new InProgress(name, null);
    }
}
