package ngodanghieu.doan.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static final int EXPIRATION = 60 * 30;
    public static final String CASH_EN = "Cash";
    public static final String VISA_EN = "Visa";
    public static final String PAYPAL_EN = "Paypal";
    public static final String CREDIT_DEBIT_ON_DELIVERY_EN = "Credit/Debit on delivery";
    public static final String PAYPAL_ES = "Paypal";
    public static final String VISA_ES = "Visa";

    public enum NotificationParameter {
        SOUND("default"),
        COLOR("#FFFF00");

        private String value;

        NotificationParameter(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }


    public enum AccountType {
        Private(1), Company(2), Anonymous(7);

        private int value;

        AccountType(int v) {
            this.value = v;
        }

        private static Map map = new HashMap<>();

        static {
            for (AccountType pageType : AccountType.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static AccountType valueOf(int pageType) {
            return (AccountType) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    public enum EmailType {
        Verify(1), Register(2), ChangePass(3), Payment(4), CreateNewUser(5), NewOrderToOwner(6),OrderCompleted(7),  OrderCanceled(8), OrderConfirmed(9);

        private int value;

        private EmailType(int v) {
            this.value = v;
        }

        private static Map map = new HashMap<>();

        static {
            for (EmailType pageType : EmailType.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static EmailType valueOf(int pageType) {
            return (EmailType) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    public enum Provider {
        FACEBOOK(1), GOOGLE(2), NORMAL(3), AUTHEN_TOKEN(4);

        private int value;

        Provider(int v) {
            this.value = v;
        }

        private static Map map = new HashMap<>();

        static {
            for (Provider pageType : Provider.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static Provider valueOf(int pageType) {
            return (Provider) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    public enum Order {
        New(1), Confirmed(2), Canceled(3), Done(4), Complete(5);

        private int value;

        Order(int v) {
            this.value = v;
        }

        private static Map map = new HashMap<>();

        static {
            for (Order pageType : Order.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static Order valueOf(int pageType) {
            return (Order) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    public enum Payment {
        PENDING(1), PROCESSING(2), DONE(3);

        private int value;

        Payment(int v) {
            this.value = v;
        }

        private static Map map = new HashMap<>();

        static {
            for (Payment pageType : Payment.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static Payment valueOf(int pageType) {
            return (Payment) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    public enum Status {
        Publish(1), UnPublish(0), Deleted(2), Authorize(3), InAuthorize(4), InActive(5), Used(7);

        private int value;
        private static Map map = new HashMap<>();

        Status(int value) {
            this.value = value;
        }

        static {
            for (Status pageType : Status.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static Status valueOf(int pageType) {
            return (Status) map.get(pageType);
        }

        public int getValue() {
            return value;
        }

    }

    public static final class ErrorTypeCommon {
        public static final String OK = "OK";
        public static final String NOT_FOUND_ITEM = "NOT_FOUND_ITEM";
        public static final String ERROR_PROCESS_DATA = "ERROR_PROCESS_DATA";
        public static final String INVALID_INPUT = "INVALID_INPUT";
        public static final String PHONE_EXISTS = "PHONE_EXISTS";
        public static final String INVALID_PHONE = "INVALID_PHONE";
        public static final String REQUEST_IS_NULL = "REQUEST_IS_NULL";
        public static final String FORM_DATA_INVALID = "FORM_DATA_INVALID";
        public static final String REQUIRED_FIELD = "REQUIRED_FIELD";
        public static final String DELETE_MANY = "Error when delete item";
        public static final String EXIST_ITEM = "EXIST_ITEM";
        public static final String Access_Denied = "Access_Denied";
    }

    public static final class ErrorCodeUserApi {
        public static final String ACCOUNT_IS_DELETE = "ACCOUNT_IS_DELETE";
        public static final String PASSWORD_NOT_MATCH = "PASSWORD_NOT_MATCH";
        public static final String ACCOUNT_NOT_ACTIVE = "ACCOUNT_NOT_ACTIVE";
        public static final String OLD_PASS_NOT_MATCH = "OLD_PASS_NOT_MATCH";
        public static final String INVALID_ACCOUNT = "INVALID_ACCOUNT";
        public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
        public static final String ACCOUNT_ACTIVE = "ACCOUNT_ACTIVE";

    }

    public static final String ROLE_CODE_OWNER = "owner";

    public static final String ROLE_CODE_GUEST = "user";

    public enum Weekday{
        SUN(0), MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6);

        private int value;

        Weekday(int v) {
            this.value = v;
        }

        private static Map map = new HashMap<>();

        static {
            for (Weekday day : Weekday.values()) {
                map.put(day.value, day);
            }
        }

        public static Weekday valueOf(int day) {
            return (Weekday) map.get(day);
        }

        public int getValue() {
            return value;
        }

    }

    //Firebase Cloud Message
    public static class Notification {
        // new order to user
        public static String NEW_ORDER_TO_USER_TITLE_EN = "New order";
        public static String NEW_ORDER_TO_USER_CONTENT_EN = "Your new order %s has been informed to VinHome";

        // new order to owner
        public static String NEW_ORDER_TO_OWNER_TITLE_EN = "You have a new Order";

        // order canceled
        public static String CANCELED_ORDER_TO_USER_TITLE_EN = "Your order has been canceled";
    }
    public final static Long TIME_EXPIRED_OTP = 15 * 60 * 1000L;
}
