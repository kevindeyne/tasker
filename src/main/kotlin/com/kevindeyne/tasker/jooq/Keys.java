/*
 * This file is generated by jOOQ.
*/
package com.kevindeyne.tasker.jooq;


import com.kevindeyne.tasker.jooq.tables.ActivationPending;
import com.kevindeyne.tasker.jooq.tables.Branch;
import com.kevindeyne.tasker.jooq.tables.Comments;
import com.kevindeyne.tasker.jooq.tables.Event;
import com.kevindeyne.tasker.jooq.tables.FlywaySchemaHistory;
import com.kevindeyne.tasker.jooq.tables.Invitation;
import com.kevindeyne.tasker.jooq.tables.Issue;
import com.kevindeyne.tasker.jooq.tables.Knowledge;
import com.kevindeyne.tasker.jooq.tables.Project;
import com.kevindeyne.tasker.jooq.tables.ProjectUsers;
import com.kevindeyne.tasker.jooq.tables.Releases;
import com.kevindeyne.tasker.jooq.tables.ReleasesChangelog;
import com.kevindeyne.tasker.jooq.tables.Search;
import com.kevindeyne.tasker.jooq.tables.Sprint;
import com.kevindeyne.tasker.jooq.tables.Tag;
import com.kevindeyne.tasker.jooq.tables.Tagcloud;
import com.kevindeyne.tasker.jooq.tables.Timesheet;
import com.kevindeyne.tasker.jooq.tables.User;
import com.kevindeyne.tasker.jooq.tables.UserRole;
import com.kevindeyne.tasker.jooq.tables.VersionIssue;
import com.kevindeyne.tasker.jooq.tables.Versions;
import com.kevindeyne.tasker.jooq.tables.records.ActivationPendingRecord;
import com.kevindeyne.tasker.jooq.tables.records.BranchRecord;
import com.kevindeyne.tasker.jooq.tables.records.CommentsRecord;
import com.kevindeyne.tasker.jooq.tables.records.EventRecord;
import com.kevindeyne.tasker.jooq.tables.records.FlywaySchemaHistoryRecord;
import com.kevindeyne.tasker.jooq.tables.records.InvitationRecord;
import com.kevindeyne.tasker.jooq.tables.records.IssueRecord;
import com.kevindeyne.tasker.jooq.tables.records.KnowledgeRecord;
import com.kevindeyne.tasker.jooq.tables.records.ProjectRecord;
import com.kevindeyne.tasker.jooq.tables.records.ProjectUsersRecord;
import com.kevindeyne.tasker.jooq.tables.records.ReleasesChangelogRecord;
import com.kevindeyne.tasker.jooq.tables.records.ReleasesRecord;
import com.kevindeyne.tasker.jooq.tables.records.SearchRecord;
import com.kevindeyne.tasker.jooq.tables.records.SprintRecord;
import com.kevindeyne.tasker.jooq.tables.records.TagRecord;
import com.kevindeyne.tasker.jooq.tables.records.TagcloudRecord;
import com.kevindeyne.tasker.jooq.tables.records.TimesheetRecord;
import com.kevindeyne.tasker.jooq.tables.records.UserRecord;
import com.kevindeyne.tasker.jooq.tables.records.UserRoleRecord;
import com.kevindeyne.tasker.jooq.tables.records.VersionIssueRecord;
import com.kevindeyne.tasker.jooq.tables.records.VersionsRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
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
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ActivationPendingRecord, Long> IDENTITY_ACTIVATION_PENDING = Identities0.IDENTITY_ACTIVATION_PENDING;
    public static final Identity<BranchRecord, Long> IDENTITY_BRANCH = Identities0.IDENTITY_BRANCH;
    public static final Identity<CommentsRecord, Long> IDENTITY_COMMENTS = Identities0.IDENTITY_COMMENTS;
    public static final Identity<EventRecord, Long> IDENTITY_EVENT = Identities0.IDENTITY_EVENT;
    public static final Identity<InvitationRecord, Long> IDENTITY_INVITATION = Identities0.IDENTITY_INVITATION;
    public static final Identity<IssueRecord, Long> IDENTITY_ISSUE = Identities0.IDENTITY_ISSUE;
    public static final Identity<KnowledgeRecord, Long> IDENTITY_KNOWLEDGE = Identities0.IDENTITY_KNOWLEDGE;
    public static final Identity<ProjectRecord, Long> IDENTITY_PROJECT = Identities0.IDENTITY_PROJECT;
    public static final Identity<ProjectUsersRecord, Long> IDENTITY_PROJECT_USERS = Identities0.IDENTITY_PROJECT_USERS;
    public static final Identity<ReleasesRecord, Long> IDENTITY_RELEASES = Identities0.IDENTITY_RELEASES;
    public static final Identity<ReleasesChangelogRecord, Long> IDENTITY_RELEASES_CHANGELOG = Identities0.IDENTITY_RELEASES_CHANGELOG;
    public static final Identity<SearchRecord, Long> IDENTITY_SEARCH = Identities0.IDENTITY_SEARCH;
    public static final Identity<SprintRecord, Long> IDENTITY_SPRINT = Identities0.IDENTITY_SPRINT;
    public static final Identity<TagRecord, Long> IDENTITY_TAG = Identities0.IDENTITY_TAG;
    public static final Identity<TagcloudRecord, Long> IDENTITY_TAGCLOUD = Identities0.IDENTITY_TAGCLOUD;
    public static final Identity<TimesheetRecord, Long> IDENTITY_TIMESHEET = Identities0.IDENTITY_TIMESHEET;
    public static final Identity<UserRecord, Long> IDENTITY_USER = Identities0.IDENTITY_USER;
    public static final Identity<UserRoleRecord, Long> IDENTITY_USER_ROLE = Identities0.IDENTITY_USER_ROLE;
    public static final Identity<VersionsRecord, Long> IDENTITY_VERSIONS = Identities0.IDENTITY_VERSIONS;
    public static final Identity<VersionIssueRecord, Long> IDENTITY_VERSION_ISSUE = Identities0.IDENTITY_VERSION_ISSUE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ActivationPendingRecord> KEY_ACTIVATION_PENDING_PRIMARY = UniqueKeys0.KEY_ACTIVATION_PENDING_PRIMARY;
    public static final UniqueKey<BranchRecord> KEY_BRANCH_PRIMARY = UniqueKeys0.KEY_BRANCH_PRIMARY;
    public static final UniqueKey<CommentsRecord> KEY_COMMENTS_PRIMARY = UniqueKeys0.KEY_COMMENTS_PRIMARY;
    public static final UniqueKey<EventRecord> KEY_EVENT_PRIMARY = UniqueKeys0.KEY_EVENT_PRIMARY;
    public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = UniqueKeys0.KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY;
    public static final UniqueKey<InvitationRecord> KEY_INVITATION_PRIMARY = UniqueKeys0.KEY_INVITATION_PRIMARY;
    public static final UniqueKey<IssueRecord> KEY_ISSUE_PRIMARY = UniqueKeys0.KEY_ISSUE_PRIMARY;
    public static final UniqueKey<KnowledgeRecord> KEY_KNOWLEDGE_PRIMARY = UniqueKeys0.KEY_KNOWLEDGE_PRIMARY;
    public static final UniqueKey<ProjectRecord> KEY_PROJECT_PRIMARY = UniqueKeys0.KEY_PROJECT_PRIMARY;
    public static final UniqueKey<ProjectUsersRecord> KEY_PROJECT_USERS_PRIMARY = UniqueKeys0.KEY_PROJECT_USERS_PRIMARY;
    public static final UniqueKey<ReleasesRecord> KEY_RELEASES_PRIMARY = UniqueKeys0.KEY_RELEASES_PRIMARY;
    public static final UniqueKey<ReleasesChangelogRecord> KEY_RELEASES_CHANGELOG_PRIMARY = UniqueKeys0.KEY_RELEASES_CHANGELOG_PRIMARY;
    public static final UniqueKey<SearchRecord> KEY_SEARCH_PRIMARY = UniqueKeys0.KEY_SEARCH_PRIMARY;
    public static final UniqueKey<SprintRecord> KEY_SPRINT_PRIMARY = UniqueKeys0.KEY_SPRINT_PRIMARY;
    public static final UniqueKey<TagRecord> KEY_TAG_PRIMARY = UniqueKeys0.KEY_TAG_PRIMARY;
    public static final UniqueKey<TagcloudRecord> KEY_TAGCLOUD_PRIMARY = UniqueKeys0.KEY_TAGCLOUD_PRIMARY;
    public static final UniqueKey<TimesheetRecord> KEY_TIMESHEET_PRIMARY = UniqueKeys0.KEY_TIMESHEET_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;
    public static final UniqueKey<UserRoleRecord> KEY_USER_ROLE_PRIMARY = UniqueKeys0.KEY_USER_ROLE_PRIMARY;
    public static final UniqueKey<VersionsRecord> KEY_VERSIONS_PRIMARY = UniqueKeys0.KEY_VERSIONS_PRIMARY;
    public static final UniqueKey<VersionIssueRecord> KEY_VERSION_ISSUE_PRIMARY = UniqueKeys0.KEY_VERSION_ISSUE_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ActivationPendingRecord, UserRecord> ACTIVATION_PENDING_IBFK_1 = ForeignKeys0.ACTIVATION_PENDING_IBFK_1;
    public static final ForeignKey<BranchRecord, ProjectRecord> BRANCH_IBFK_1 = ForeignKeys0.BRANCH_IBFK_1;
    public static final ForeignKey<CommentsRecord, UserRecord> COMMENTS_IBFK_1 = ForeignKeys0.COMMENTS_IBFK_1;
    public static final ForeignKey<CommentsRecord, IssueRecord> COMMENTS_IBFK_2 = ForeignKeys0.COMMENTS_IBFK_2;
    public static final ForeignKey<IssueRecord, SprintRecord> ISSUE_IBFK_1 = ForeignKeys0.ISSUE_IBFK_1;
    public static final ForeignKey<IssueRecord, ProjectRecord> ISSUE_IBFK_2 = ForeignKeys0.ISSUE_IBFK_2;
    public static final ForeignKey<KnowledgeRecord, UserRecord> KNOWLEDGE_IBFK_1 = ForeignKeys0.KNOWLEDGE_IBFK_1;
    public static final ForeignKey<KnowledgeRecord, TagRecord> KNOWLEDGE_IBFK_2 = ForeignKeys0.KNOWLEDGE_IBFK_2;
    public static final ForeignKey<ProjectUsersRecord, ProjectRecord> PROJECT_USERS_IBFK_1 = ForeignKeys0.PROJECT_USERS_IBFK_1;
    public static final ForeignKey<ProjectUsersRecord, UserRecord> PROJECT_USERS_IBFK_2 = ForeignKeys0.PROJECT_USERS_IBFK_2;
    public static final ForeignKey<ReleasesChangelogRecord, ReleasesRecord> RELEASES_CHANGELOG_IBFK_2 = ForeignKeys0.RELEASES_CHANGELOG_IBFK_2;
    public static final ForeignKey<ReleasesChangelogRecord, IssueRecord> RELEASES_CHANGELOG_IBFK_1 = ForeignKeys0.RELEASES_CHANGELOG_IBFK_1;
    public static final ForeignKey<SprintRecord, ProjectRecord> SPRINT_IBFK_1 = ForeignKeys0.SPRINT_IBFK_1;
    public static final ForeignKey<TagcloudRecord, IssueRecord> TAGCLOUD_IBFK_1 = ForeignKeys0.TAGCLOUD_IBFK_1;
    public static final ForeignKey<TagcloudRecord, TagRecord> TAGCLOUD_IBFK_2 = ForeignKeys0.TAGCLOUD_IBFK_2;
    public static final ForeignKey<TimesheetRecord, IssueRecord> TIMESHEET_IBFK_1 = ForeignKeys0.TIMESHEET_IBFK_1;
    public static final ForeignKey<TimesheetRecord, UserRecord> TIMESHEET_IBFK_2 = ForeignKeys0.TIMESHEET_IBFK_2;
    public static final ForeignKey<UserRoleRecord, UserRecord> USER_ROLE_IBFK_1 = ForeignKeys0.USER_ROLE_IBFK_1;
    public static final ForeignKey<VersionsRecord, ProjectRecord> VERSIONS_IBFK_1 = ForeignKeys0.VERSIONS_IBFK_1;
    public static final ForeignKey<VersionsRecord, BranchRecord> VERSIONS_IBFK_2 = ForeignKeys0.VERSIONS_IBFK_2;
    public static final ForeignKey<VersionIssueRecord, VersionsRecord> VERSION_ISSUE_IBFK_1 = ForeignKeys0.VERSION_ISSUE_IBFK_1;
    public static final ForeignKey<VersionIssueRecord, IssueRecord> VERSION_ISSUE_IBFK_2 = ForeignKeys0.VERSION_ISSUE_IBFK_2;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<ActivationPendingRecord, Long> IDENTITY_ACTIVATION_PENDING = createIdentity(ActivationPending.ACTIVATION_PENDING, ActivationPending.ACTIVATION_PENDING.ID);
        public static Identity<BranchRecord, Long> IDENTITY_BRANCH = createIdentity(Branch.BRANCH, Branch.BRANCH.ID);
        public static Identity<CommentsRecord, Long> IDENTITY_COMMENTS = createIdentity(Comments.COMMENTS, Comments.COMMENTS.ID);
        public static Identity<EventRecord, Long> IDENTITY_EVENT = createIdentity(Event.EVENT, Event.EVENT.ID);
        public static Identity<InvitationRecord, Long> IDENTITY_INVITATION = createIdentity(Invitation.INVITATION, Invitation.INVITATION.ID);
        public static Identity<IssueRecord, Long> IDENTITY_ISSUE = createIdentity(Issue.ISSUE, Issue.ISSUE.ID);
        public static Identity<KnowledgeRecord, Long> IDENTITY_KNOWLEDGE = createIdentity(Knowledge.KNOWLEDGE, Knowledge.KNOWLEDGE.ID);
        public static Identity<ProjectRecord, Long> IDENTITY_PROJECT = createIdentity(Project.PROJECT, Project.PROJECT.ID);
        public static Identity<ProjectUsersRecord, Long> IDENTITY_PROJECT_USERS = createIdentity(ProjectUsers.PROJECT_USERS, ProjectUsers.PROJECT_USERS.ID);
        public static Identity<ReleasesRecord, Long> IDENTITY_RELEASES = createIdentity(Releases.RELEASES, Releases.RELEASES.ID);
        public static Identity<ReleasesChangelogRecord, Long> IDENTITY_RELEASES_CHANGELOG = createIdentity(ReleasesChangelog.RELEASES_CHANGELOG, ReleasesChangelog.RELEASES_CHANGELOG.ID);
        public static Identity<SearchRecord, Long> IDENTITY_SEARCH = createIdentity(Search.SEARCH, Search.SEARCH.ID);
        public static Identity<SprintRecord, Long> IDENTITY_SPRINT = createIdentity(Sprint.SPRINT, Sprint.SPRINT.ID);
        public static Identity<TagRecord, Long> IDENTITY_TAG = createIdentity(Tag.TAG, Tag.TAG.ID);
        public static Identity<TagcloudRecord, Long> IDENTITY_TAGCLOUD = createIdentity(Tagcloud.TAGCLOUD, Tagcloud.TAGCLOUD.ID);
        public static Identity<TimesheetRecord, Long> IDENTITY_TIMESHEET = createIdentity(Timesheet.TIMESHEET, Timesheet.TIMESHEET.ID);
        public static Identity<UserRecord, Long> IDENTITY_USER = createIdentity(User.USER, User.USER.ID);
        public static Identity<UserRoleRecord, Long> IDENTITY_USER_ROLE = createIdentity(UserRole.USER_ROLE, UserRole.USER_ROLE.ID);
        public static Identity<VersionsRecord, Long> IDENTITY_VERSIONS = createIdentity(Versions.VERSIONS, Versions.VERSIONS.ID);
        public static Identity<VersionIssueRecord, Long> IDENTITY_VERSION_ISSUE = createIdentity(VersionIssue.VERSION_ISSUE, VersionIssue.VERSION_ISSUE.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<ActivationPendingRecord> KEY_ACTIVATION_PENDING_PRIMARY = createUniqueKey(ActivationPending.ACTIVATION_PENDING, "KEY_activation_pending_PRIMARY", ActivationPending.ACTIVATION_PENDING.ID);
        public static final UniqueKey<BranchRecord> KEY_BRANCH_PRIMARY = createUniqueKey(Branch.BRANCH, "KEY_branch_PRIMARY", Branch.BRANCH.ID);
        public static final UniqueKey<CommentsRecord> KEY_COMMENTS_PRIMARY = createUniqueKey(Comments.COMMENTS, "KEY_comments_PRIMARY", Comments.COMMENTS.ID);
        public static final UniqueKey<EventRecord> KEY_EVENT_PRIMARY = createUniqueKey(Event.EVENT, "KEY_event_PRIMARY", Event.EVENT.ID);
        public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "KEY_flyway_schema_history_PRIMARY", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<InvitationRecord> KEY_INVITATION_PRIMARY = createUniqueKey(Invitation.INVITATION, "KEY_invitation_PRIMARY", Invitation.INVITATION.ID);
        public static final UniqueKey<IssueRecord> KEY_ISSUE_PRIMARY = createUniqueKey(Issue.ISSUE, "KEY_issue_PRIMARY", Issue.ISSUE.ID);
        public static final UniqueKey<KnowledgeRecord> KEY_KNOWLEDGE_PRIMARY = createUniqueKey(Knowledge.KNOWLEDGE, "KEY_knowledge_PRIMARY", Knowledge.KNOWLEDGE.ID);
        public static final UniqueKey<ProjectRecord> KEY_PROJECT_PRIMARY = createUniqueKey(Project.PROJECT, "KEY_project_PRIMARY", Project.PROJECT.ID);
        public static final UniqueKey<ProjectUsersRecord> KEY_PROJECT_USERS_PRIMARY = createUniqueKey(ProjectUsers.PROJECT_USERS, "KEY_project_users_PRIMARY", ProjectUsers.PROJECT_USERS.ID);
        public static final UniqueKey<ReleasesRecord> KEY_RELEASES_PRIMARY = createUniqueKey(Releases.RELEASES, "KEY_releases_PRIMARY", Releases.RELEASES.ID);
        public static final UniqueKey<ReleasesChangelogRecord> KEY_RELEASES_CHANGELOG_PRIMARY = createUniqueKey(ReleasesChangelog.RELEASES_CHANGELOG, "KEY_releases_changelog_PRIMARY", ReleasesChangelog.RELEASES_CHANGELOG.ID);
        public static final UniqueKey<SearchRecord> KEY_SEARCH_PRIMARY = createUniqueKey(Search.SEARCH, "KEY_search_PRIMARY", Search.SEARCH.ID);
        public static final UniqueKey<SprintRecord> KEY_SPRINT_PRIMARY = createUniqueKey(Sprint.SPRINT, "KEY_sprint_PRIMARY", Sprint.SPRINT.ID);
        public static final UniqueKey<TagRecord> KEY_TAG_PRIMARY = createUniqueKey(Tag.TAG, "KEY_tag_PRIMARY", Tag.TAG.ID);
        public static final UniqueKey<TagcloudRecord> KEY_TAGCLOUD_PRIMARY = createUniqueKey(Tagcloud.TAGCLOUD, "KEY_tagcloud_PRIMARY", Tagcloud.TAGCLOUD.ID);
        public static final UniqueKey<TimesheetRecord> KEY_TIMESHEET_PRIMARY = createUniqueKey(Timesheet.TIMESHEET, "KEY_timesheet_PRIMARY", Timesheet.TIMESHEET.ID);
        public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = createUniqueKey(User.USER, "KEY_user_PRIMARY", User.USER.ID);
        public static final UniqueKey<UserRoleRecord> KEY_USER_ROLE_PRIMARY = createUniqueKey(UserRole.USER_ROLE, "KEY_user_role_PRIMARY", UserRole.USER_ROLE.ID);
        public static final UniqueKey<VersionsRecord> KEY_VERSIONS_PRIMARY = createUniqueKey(Versions.VERSIONS, "KEY_versions_PRIMARY", Versions.VERSIONS.ID);
        public static final UniqueKey<VersionIssueRecord> KEY_VERSION_ISSUE_PRIMARY = createUniqueKey(VersionIssue.VERSION_ISSUE, "KEY_version_issue_PRIMARY", VersionIssue.VERSION_ISSUE.ID);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<ActivationPendingRecord, UserRecord> ACTIVATION_PENDING_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_USER_PRIMARY, ActivationPending.ACTIVATION_PENDING, "activation_pending_ibfk_1", ActivationPending.ACTIVATION_PENDING.USER_ID);
        public static final ForeignKey<BranchRecord, ProjectRecord> BRANCH_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_PROJECT_PRIMARY, Branch.BRANCH, "branch_ibfk_1", Branch.BRANCH.PROJECT_ID);
        public static final ForeignKey<CommentsRecord, UserRecord> COMMENTS_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_USER_PRIMARY, Comments.COMMENTS, "comments_ibfk_1", Comments.COMMENTS.USER_ID);
        public static final ForeignKey<CommentsRecord, IssueRecord> COMMENTS_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_ISSUE_PRIMARY, Comments.COMMENTS, "comments_ibfk_2", Comments.COMMENTS.ISSUE_ID);
        public static final ForeignKey<IssueRecord, SprintRecord> ISSUE_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_SPRINT_PRIMARY, Issue.ISSUE, "issue_ibfk_1", Issue.ISSUE.SPRINT_ID);
        public static final ForeignKey<IssueRecord, ProjectRecord> ISSUE_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_PROJECT_PRIMARY, Issue.ISSUE, "issue_ibfk_2", Issue.ISSUE.PROJECT_ID);
        public static final ForeignKey<KnowledgeRecord, UserRecord> KNOWLEDGE_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_USER_PRIMARY, Knowledge.KNOWLEDGE, "knowledge_ibfk_1", Knowledge.KNOWLEDGE.USER_ID);
        public static final ForeignKey<KnowledgeRecord, TagRecord> KNOWLEDGE_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_TAG_PRIMARY, Knowledge.KNOWLEDGE, "knowledge_ibfk_2", Knowledge.KNOWLEDGE.TAG_ID);
        public static final ForeignKey<ProjectUsersRecord, ProjectRecord> PROJECT_USERS_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_PROJECT_PRIMARY, ProjectUsers.PROJECT_USERS, "project_users_ibfk_1", ProjectUsers.PROJECT_USERS.PROJECT_ID);
        public static final ForeignKey<ProjectUsersRecord, UserRecord> PROJECT_USERS_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_USER_PRIMARY, ProjectUsers.PROJECT_USERS, "project_users_ibfk_2", ProjectUsers.PROJECT_USERS.USER_ID);
        public static final ForeignKey<ReleasesChangelogRecord, ReleasesRecord> RELEASES_CHANGELOG_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_RELEASES_PRIMARY, ReleasesChangelog.RELEASES_CHANGELOG, "releases_changelog_ibfk_2", ReleasesChangelog.RELEASES_CHANGELOG.RELEASE_ID);
        public static final ForeignKey<ReleasesChangelogRecord, IssueRecord> RELEASES_CHANGELOG_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_ISSUE_PRIMARY, ReleasesChangelog.RELEASES_CHANGELOG, "releases_changelog_ibfk_1", ReleasesChangelog.RELEASES_CHANGELOG.ISSUE_ID);
        public static final ForeignKey<SprintRecord, ProjectRecord> SPRINT_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_PROJECT_PRIMARY, Sprint.SPRINT, "sprint_ibfk_1", Sprint.SPRINT.PROJECT_ID);
        public static final ForeignKey<TagcloudRecord, IssueRecord> TAGCLOUD_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_ISSUE_PRIMARY, Tagcloud.TAGCLOUD, "tagcloud_ibfk_1", Tagcloud.TAGCLOUD.ISSUE_ID);
        public static final ForeignKey<TagcloudRecord, TagRecord> TAGCLOUD_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_TAG_PRIMARY, Tagcloud.TAGCLOUD, "tagcloud_ibfk_2", Tagcloud.TAGCLOUD.TAG_ID);
        public static final ForeignKey<TimesheetRecord, IssueRecord> TIMESHEET_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_ISSUE_PRIMARY, Timesheet.TIMESHEET, "timesheet_ibfk_1", Timesheet.TIMESHEET.ISSUE_ID);
        public static final ForeignKey<TimesheetRecord, UserRecord> TIMESHEET_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_USER_PRIMARY, Timesheet.TIMESHEET, "timesheet_ibfk_2", Timesheet.TIMESHEET.USER_ID);
        public static final ForeignKey<UserRoleRecord, UserRecord> USER_ROLE_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_USER_PRIMARY, UserRole.USER_ROLE, "user_role_ibfk_1", UserRole.USER_ROLE.USER_ID);
        public static final ForeignKey<VersionsRecord, ProjectRecord> VERSIONS_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_PROJECT_PRIMARY, Versions.VERSIONS, "versions_ibfk_1", Versions.VERSIONS.PROJECT_ID);
        public static final ForeignKey<VersionsRecord, BranchRecord> VERSIONS_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_BRANCH_PRIMARY, Versions.VERSIONS, "versions_ibfk_2", Versions.VERSIONS.BRANCH_ID);
        public static final ForeignKey<VersionIssueRecord, VersionsRecord> VERSION_ISSUE_IBFK_1 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_VERSIONS_PRIMARY, VersionIssue.VERSION_ISSUE, "version_issue_ibfk_1", VersionIssue.VERSION_ISSUE.VERSIONS_ID);
        public static final ForeignKey<VersionIssueRecord, IssueRecord> VERSION_ISSUE_IBFK_2 = createForeignKey(com.kevindeyne.tasker.jooq.Keys.KEY_ISSUE_PRIMARY, VersionIssue.VERSION_ISSUE, "version_issue_ibfk_2", VersionIssue.VERSION_ISSUE.ISSUE_ID);
    }
}
