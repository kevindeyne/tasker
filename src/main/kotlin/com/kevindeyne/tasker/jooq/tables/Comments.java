/*
 * This file is generated by jOOQ.
*/
package com.kevindeyne.tasker.jooq.tables;


import com.kevindeyne.tasker.jooq.Indexes;
import com.kevindeyne.tasker.jooq.Keys;
import com.kevindeyne.tasker.jooq.Taskr;
import com.kevindeyne.tasker.jooq.tables.records.CommentsRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class Comments extends TableImpl<CommentsRecord> {

    private static final long serialVersionUID = 1572133164;

    /**
     * The reference instance of <code>taskr.comments</code>
     */
    public static final Comments COMMENTS = new Comments();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CommentsRecord> getRecordType() {
        return CommentsRecord.class;
    }

    /**
     * The column <code>taskr.comments.id</code>.
     */
    public final TableField<CommentsRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>taskr.comments.user_id</code>.
     */
    public final TableField<CommentsRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>taskr.comments.issue_id</code>.
     */
    public final TableField<CommentsRecord, Long> ISSUE_ID = createField("issue_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>taskr.comments.post_date</code>.
     */
    public final TableField<CommentsRecord, Timestamp> POST_DATE = createField("post_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>taskr.comments.message</code>.
     */
    public final TableField<CommentsRecord, String> MESSAGE = createField("message", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>taskr.comments</code> table reference
     */
    public Comments() {
        this(DSL.name("comments"), null);
    }

    /**
     * Create an aliased <code>taskr.comments</code> table reference
     */
    public Comments(String alias) {
        this(DSL.name(alias), COMMENTS);
    }

    /**
     * Create an aliased <code>taskr.comments</code> table reference
     */
    public Comments(Name alias) {
        this(alias, COMMENTS);
    }

    private Comments(Name alias, Table<CommentsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Comments(Name alias, Table<CommentsRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.COMMENTS_ISSUE_ID, Indexes.COMMENTS_PRIMARY, Indexes.COMMENTS_USER_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CommentsRecord, Long> getIdentity() {
        return Keys.IDENTITY_COMMENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CommentsRecord> getPrimaryKey() {
        return Keys.KEY_COMMENTS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CommentsRecord>> getKeys() {
        return Arrays.<UniqueKey<CommentsRecord>>asList(Keys.KEY_COMMENTS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CommentsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CommentsRecord, ?>>asList(Keys.COMMENTS_IBFK_1, Keys.COMMENTS_IBFK_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comments as(String alias) {
        return new Comments(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comments as(Name alias) {
        return new Comments(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Comments rename(String name) {
        return new Comments(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Comments rename(Name name) {
        return new Comments(name, null);
    }
}
