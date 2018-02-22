/*
 * This file is generated by jOOQ.
*/
package com.kevindeyne.tasker.jooq.tables.records;


import com.kevindeyne.tasker.jooq.tables.VersionIssue;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


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
public class VersionIssueRecord extends UpdatableRecordImpl<VersionIssueRecord> implements Record3<Long, Long, Long> {

    private static final long serialVersionUID = 1749314844;

    /**
     * Setter for <code>taskr.version_issue.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>taskr.version_issue.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>taskr.version_issue.versions_id</code>.
     */
    public void setVersionsId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>taskr.version_issue.versions_id</code>.
     */
    public Long getVersionsId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>taskr.version_issue.issue_id</code>.
     */
    public void setIssueId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>taskr.version_issue.issue_id</code>.
     */
    public Long getIssueId() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, Long, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return VersionIssue.VERSION_ISSUE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return VersionIssue.VERSION_ISSUE.VERSIONS_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return VersionIssue.VERSION_ISSUE.ISSUE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getVersionsId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component3() {
        return getIssueId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getVersionsId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getIssueId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionIssueRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionIssueRecord value2(Long value) {
        setVersionsId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionIssueRecord value3(Long value) {
        setIssueId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionIssueRecord values(Long value1, Long value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VersionIssueRecord
     */
    public VersionIssueRecord() {
        super(VersionIssue.VERSION_ISSUE);
    }

    /**
     * Create a detached, initialised VersionIssueRecord
     */
    public VersionIssueRecord(Long id, Long versionsId, Long issueId) {
        super(VersionIssue.VERSION_ISSUE);

        set(0, id);
        set(1, versionsId);
        set(2, issueId);
    }
}