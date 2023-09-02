package com.fssa.reparo.validation;
import com.fssa.reparo.exception.InvalidEntryException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Validations {
    private static  final String NAME_REGEX = "^[A-Za-z\\s]+$";
    private static  final String PASS = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";
    private static  final String ADDRESS_PATTERN = "^[a-zA-Z0-9\\s.,'#\\-]+(\\s[A-Za-z0-9\\-#]+)?$";
    private static final String VEHICLE_NUMBER_PATTERN = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$";



    public boolean stringValidation(String str) throws InvalidEntryException {
        Matcher match;
        try {
            Pattern pat = Pattern.compile(NAME_REGEX);
            if (str == null) {
                throw new InvalidEntryException("input can't be null");
            }
            if (str.length() > 20){
                throw new InvalidEntryException("Number of characters should be less than 20");
            }
            match = pat.matcher(str);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid String");
        }
        if(!match.matches()) throw new InvalidEntryException("Input should only be in Alphabets ");

        return match.matches();
    }

    public boolean numberValidation(long number) throws InvalidEntryException {

        try {

            String num = Long.toString(number);
            if(num.length() > 10) throw new InvalidEntryException("number Should be in 10 digits");
            return num.length() == 10;


        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid Number ");
        }


    }

    public boolean passWordValidation(String s) throws InvalidEntryException {
        Matcher match;
        try {

            Pattern pt = Pattern.compile(PASS);
            match = pt.matcher(s);
            if(!match.matches()) throw  new InvalidEntryException("Password should be in AlphaNumeric characters Eg:abc123");
            return match.matches();
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid Password");
        }
    }
    public boolean workshopType(int i ){
        return i == 2 || i == 3 || i == 4;
    }
    public  boolean addressValidation(String address) throws InvalidEntryException {
        Matcher match;
        try {
            Pattern pat = Pattern.compile(ADDRESS_PATTERN);

            match = pat.matcher(address);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid address");
        }
        return match.matches();


    }
    public boolean vehicleNumberValidation(String num ) throws  InvalidEntryException{
        Matcher match;
        try {
            Pattern pat = Pattern.compile(VEHICLE_NUMBER_PATTERN);

            match = pat.matcher(num);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid vehicle number");
        }
        if(!match.matches()) throw new InvalidEntryException("Vehicle number should be Alphanumeric characters");
        return match.matches();

    }
    public boolean vehicleYearValidation(int yr) throws InvalidEntryException {
        try {
            String year = Integer.toString(yr);
            if (year.length() != 4)  return  false;
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if(yr>currentYear) throw new InvalidEntryException("Year Can't be in future");
            else return true;

        } catch (Exception e) {
            throw new InvalidEntryException(e);
        }
    }
    public boolean  loginCredentialValidation(long num ,String pass) throws InvalidEntryException{
        try {
            return numberValidation(num) && passWordValidation(pass);
        } catch (InvalidEntryException e) {
            throw new InvalidEntryException(e);
        }
    }

    public void priceValidation(int price) throws InvalidEntryException{
        if(price>9999){
            throw new InvalidEntryException("price can't be more than 4 digits");
        }
        if(price<0){
            throw new InvalidEntryException("price can't be less than 0");
        }
    }



}
