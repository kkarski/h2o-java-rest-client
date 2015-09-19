package water.bindings.pojos;

public class ValidationMessageV3 extends ValidationMessageBase {
    /** Type of validation message (ERROR, WARN, INFO, HIDE) */
    public String message_type;

    /** Field to which the message applies */
    public String field_name;

    /** Message text */
    public String message;
}
