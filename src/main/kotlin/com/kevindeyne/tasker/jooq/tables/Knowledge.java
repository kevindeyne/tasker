/*
 * This file is generated by jOOQ.
*/
package com.kevindeyne.tasker.jooq.tables;


import com.kevindeyne.tasker.jooq.Indexes;
import com.kevindeyne.tasker.jooq.Keys;
import com.kevindeyne.tasker.jooq.Taskr;
import com.kevindeyne.tasker.jooq.tables.records.KnowledgeRecord;

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
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Knowledge extends TableImpl<KnowledgeRecord> {

    private static final long serialVersionUID = 485215292;

    /**
     * The reference instance of <code>taskr.knowledge</code>
     */
    public static final Knowledge KNOWLEDGE = new Knowledge();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<KnowledgeRecord> getRecordType() {
        return KnowledgeRecord.class;
    }

    /**
     * The column <code>taskr.knowledge.id</code>.
     */
    public final TableField<KnowledgeRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>taskr.knowledge.user_id</code>.
     */
    public final TableField<KnowledgeRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>taskr.knowledge.tag_id</code>.
     */
    public final TableField<KnowledgeRecord, Long> TAG_ID = createField("tag_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>taskr.knowledge</code> table reference
     */
    public Knowledge() {
        this(DSL.name("knowledge"), null);
    }

    /**
     * Create an aliased <code>taskr.knowledge</code> table reference
     */
    public Knowledge(String alias) {
        this(DSL.name(alias), KNOWLEDGE);
    }

    /**
     * Create an aliased <code>taskr.knowledge</code> table reference
     */
    public Knowledge(Name alias) {
        this(alias, KNOWLEDGE);
    }

    private Knowledge(Name alias, Table<KnowledgeRecord> aliased) {
        this(alias, aliased, null);
    }

    private Knowledge(Name alias, Table<KnowledgeRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.KNOWLEDGE_PRIMARY, Indexes.KNOWLEDGE_TAG_ID, Indexes.KNOWLEDGE_USER_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<KnowledgeRecord, Long> getIdentity() {
        return Keys.IDENTITY_KNOWLEDGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<KnowledgeRecord> getPrimaryKey() {
        return Keys.KEY_KNOWLEDGE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<KnowledgeRecord>> getKeys() {
        return Arrays.<UniqueKey<KnowledgeRecord>>asList(Keys.KEY_KNOWLEDGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<KnowledgeRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<KnowledgeRecord, ?>>asList(Keys.KNOWLEDGE_IBFK_1, Keys.KNOWLEDGE_IBFK_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Knowledge as(String alias) {
        return new Knowledge(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Knowledge as(Name alias) {
        return new Knowledge(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Knowledge rename(String name) {
        return new Knowledge(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Knowledge rename(Name name) {
        return new Knowledge(name, null);
    }
}
