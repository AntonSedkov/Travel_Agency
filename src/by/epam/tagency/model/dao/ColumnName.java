package by.epam.tagency.model.dao;

public class ColumnName {

    //users
    public static final String ID_USER = "id_user";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String STATUS = "status";
    public static final String EMAIL_APPROVED = "email_approved";

    //tours
    public static final String ID_TOUR = "id_tour";
    public static final String TOUR_PURPOSE = "tour_purpose";
    public static final String COUNTRY = "country";
    public static final String HOTEL_NAME = "hotel_name";
    public static final String HOTEL_STARS = "hotel_stars";
    public static final String TRANSPORT = "transport";
    public static final String DATE_START = "date_start";
    public static final String QUANTITY_OF_DAYS = "quantity_of_days";
    public static final String PRICE = "price";
    public static final String QUANTITY_TOURS = "quantity_tours";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_PATH = "image_path";
    public static final String DISCOUNT = "discount";

    //orders
    public static final String ID_ORDER = "id_order";
    public static final String DATE_ORDER = "date_order";
    public static final String STATE = "state";
    public static final String COMMENT = "comment";

    //sheet
    public static final String ID_SHEET = "id_sheet";
    public static final String SHEET_SUM = "sheet_sum";
    public static final String CUSTOMER_DISCOUNT = "customer_discount";

    //passport
    public static final String ID_PASSPORT = "id_passport";
    public static final String SURNAME = "surname";
    public static final String NAME = "name";
    public static final String BIRTH_DATE = "birth_date";
    public static final String PASSPORT_NUMBER = "passport_number";
    public static final String PASSPORT_IMAGE = "passport_image";

    //travel_docs
    public static final String ID_TRAVEL_DOCS_FK = "id_travel_docs_fk";
    public static final String ID_TRAVEL_DOCS = "id_travel_docs";
    public static final String VOUCHER = "voucher";
    public static final String INSURANCE = "insurance";
    public static final String TICKET = "ticket";

    //paycards
    public static final String ID_PAYCARD = "id_paycard";
    public static final String CARD_NUMBER = "card_number";
    public static final String CARD_SUM = "card_sum";
    public static final String CARD_QUANTITY = "card_quantity";

    //sheet_operation
    public static final String ID_OPERATION = "id_operation";
    public static final String OPERATION_SUM = "operation_sum";
    public static final String OPERATION_PURPOSE = "operation_purpose";

    private ColumnName() {
    }
}