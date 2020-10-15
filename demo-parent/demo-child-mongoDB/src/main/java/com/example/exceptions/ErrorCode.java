package com.example.exceptions;

public enum ErrorCode {

    VALIDATION_FAILED(1000),
    NO_RECORD_FOUND(1001),
    USER_NOT_FOUND(1002),
    INVALID_TOKEN(1003),
    EXPIRED_TOKEN(1004),
    MISMATCH_IN_PASSWORDS(1005),
    UNABLE_TO_SEND_EMAIL(1006),
    UNABLE_TO_UPLOAD(1007),
    ALREADY_REGISTERED_UNVERIFIED(1008),
    ALREADY_REGISTERED_SOCIAL_ACCOUNT(1009),
    ALREADY_REGISTERED_ACCOUNT(1010),
    MORE_THAN_MAX_FILE_SIZE(1011),
    INVAILID_OPERATION(1012),
    NO_CONTENT_PREVIEW_AVAILABLE(1013),
    CONTENT_CANNOT_BE_APPROVED(1014),
    ILLEGAL_CONTENT_STATE(1015),
    INVALID_ACTIVITY_STATE(1016),
    CONTENT_STATE_NOT_UPDATED(1017),
    UNABLE_TO_MOVE_FILE(1018),
    INVALID_ID(1019),
    TAG_ALREADY_EXISTS(1020),
    PROPERTY_NAME_ALREADY_EXISTS(1021),
    CONTENT_NOT_UPLOADED_PROPERLY(1022),
    GENERIC_ERROR(1022),
    ACCESS_DENIED(1023),
    NO_FILE_FOUND(1024),
    CONTENT_STATE_NOT_ADDED(1025),
    TRANSCODE_EXCEPTION(1026),
    CANNOT_DOWNLOAD_CONTENT(1027),
    INVALID_AWS_JOB_STATUS(1028),
    INVALID_DEVICE_KEY(1029),
    INVALID_DEVICE_AUTHENTICATION_KEY(1030),
    INVALID_CATEGORY_ID(1031),
    UNAUTHORIZED_FOR_ACTION(1032),
    PAIRING_CODE_MISMATCH(1033),
    DEVICE_NOT_REGISTERED(1034),
    PAIRING_CODE_NOT_PRESENT(1035),
    DEVICE_ALREADY_PAIRED(1036),
    INVALID_REQUEST(1037),
    LINK_EXPIRED(1038),
    UNABLE_TO_ELASTICSEARCH_QUERY(1039),
    CANNOT_DELETE_DEFAULT_THUMBNAIL(1040),
    UNABLE_TO_GET_AWS_PREFIX(1041),
    THUMBNAIL_OPERATIONS_BLOCKED(1042),
    DUPLICATE_CATEGORY_NAME(1043),
    ALREADY_DELETED_RECORD(1044),
    DUPLICATE_SUB_CATEGORY_NAME(1045),
    UNAUTHORIZED_ROLE_CREATION(1046),
    DUPLICATE_DEVICE_ID(1047),
    MALFORMED_JSON_DATA(1048),
    EVENT_ALREADY_EXISTS(1049),
    DUPLICATE_NAME(1050),
    INVALID_ARTIST_ID(1051),
    COULD_NOT_ADDED(1052),
    INVALID_OTP(1053),
    INVALID_TAG_ID(1054),
    INVALID_PLAYLIST_CREATION_OP(1055),
    SOMETHING_WENT_WRONG(1054),
    EMAIL_ALREADY_LINKED(1055),
    PHONE_ALREADY_LINKED(1056),
    CANNOT_UPDATE(1057),
    INVALID_ALBUM_THUMBNAIL(1058),
    INVALID_ARTIST_THUMBNAIL(1059),
    INVALID_SONG_THUMBNAIL(1060),
    INVALID_EVENT_THUMBNAIL(1061),
    TRANSCODING_INPUT_MISSING(1062),
    INVALID_SONG_ID(1063),
    INVALID_RAIL_ID(1064),
    NO_SETTINGS_FOUND(1065),
    INVALID_THUMBNAIL_ID(1066),
    NO_SONG_FOUND(1067),
    INVALID_CAMPAIGN_ID(1068),
    INVALID_USER_NOTIFICATION(1069),
    ERROR_IN_CAMPAIGN_PARAMETERS(1070),
    INVALID_EVENT_ATTENDANCE_ID(1071),
    INVALID_EVENT_ID(1072),
    EVENT_ATTENDANCE_ALREADY_MARKED(1073),
    INVALID_CHECK_IN_GUEST_UPDATE_OP(1074),
    EVENT_ALREADY_BOOKED(1075),
    INVALID_EVENT_BOOKING_ID(1076),
    DEFAULT_CITY_NOT_FOUND(1077),
    COULD_NOT_GENERATE_QR_CODE(1078),
    INVALID_EVENT_BOOKING_ID_OR_SUBEVENT_IDENTIFIER(1079),
    MOBILE_NUMBER_ALREADY_LINKED(1080),
    EMAIL_ACCOUNT_ALREADY_LINKED(1081),
    INVALID_SUBEVENTIDENTIFIER_STRING_PASSED(1082),
    INVALID_SUBEVENT_CORRESPONDING_TO_EVENT(1083),
    INVALID_ID_STRING_PASSED(1084),
    INVALID_CITY_ID(1085),
    INVALID_PHONE_NUMBER(1086),
    INCORRECT_DATABASE_CONFIGURATION(1087),
	INVALID_ALBUM_ID(1088),
	SONG_EVENT_NOT_FOUND(1089),
	UNABLE_TO_PARSE_FILE(1090),
	FILEFORMAT_NOT_SUPPORTED(1091),
	DUPLICATE_RECORD_FOUND(1092);

    private int value;

    private ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}