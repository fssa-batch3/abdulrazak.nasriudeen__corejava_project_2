package validation;
import exception.InvalidEntryException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Validations {
    static private final String nameRegex = "^[A-Za-z\\s]+$";
    static private final String pass = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";
    static private final String addressPattern = "^[a-zA-Z0-9\\s.,'#\\-]+(\\s[A-Za-z0-9\\-#]+)?$";
    static private final String vehicleNumberPattern = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$";




    public boolean stringValidation(String str) throws InvalidEntryException {
        Matcher match;


        try {
            Pattern pat = Pattern.compile(nameRegex);
            if (str == null) {
                return false;
            }if (str.length() > 15){
                return  false ;
            }
            match = pat.matcher(str);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid String");
        }
        return match.matches();
    }

    public boolean numberValidation(Long number) throws InvalidEntryException {

        try {

            String num = Long.toString(number);

            //  Pattern pat = Pattern.compile(numberRegex);
            return num.length() == 10;


        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid Number ");
        }


    }

    public boolean passWordValidation(String s) throws InvalidEntryException {
        Matcher match;
        try {
            //if(s == null) return false;
            Pattern pt = Pattern.compile(pass);
            match = pt.matcher(s);
            return match.matches();
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid Password");
        }
    }
    public boolean WorkshopType(int i ){
        return i == 2 || i == 3 || i == 4;
    }
    public  boolean addressValidation(String address) throws InvalidEntryException {
        Matcher match;
        try {
            Pattern pat = Pattern.compile(addressPattern);

            match = pat.matcher(address);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid address");
        }
        return match.matches();


    }
    public boolean vehicleNumberValidation(String num ) throws  InvalidEntryException{
        Matcher match;
        try {
            Pattern pat = Pattern.compile(vehicleNumberPattern);

            match = pat.matcher(num);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid vehicle number");
        }
        return match.matches();

    }
    public boolean vehicleYearValidation(int yr) throws InvalidEntryException {
        try {
            String year = Integer.toString(yr);
            if (year.length() != 4) return false;
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            return yr <= currentYear;

        } catch (Exception e) {
            throw new InvalidEntryException(e);
        }
    }



}
