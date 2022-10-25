package att.onboarding.model;


public enum Status {

    //Admin created user
    CREATED_INCOMPLETE,

    //User created their own password, auth token expires
    CREATED_INCOMPLETE_WITH_PASSWORD,

    //Users profile is complete and approved
    COMPLETE,

    //Admin statuses

    DISABLED,
    DISABLED_FOR_DELETE,
    DISABLED_FOR_EDIT
}
