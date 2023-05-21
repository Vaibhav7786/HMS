
public class SetValidation {

    static boolean isValidContactNumber(String contactNumber) {
        if (contactNumber.length() != 10) {
            return false;
        }
        for (int i = 0; i < contactNumber.length(); i++) {
            if (!Character.isDigit(contactNumber.charAt(i))) {
                return false;
            }
        }
        return contactNumber.charAt(0) >= '6' && contactNumber.charAt(0) <= '9';
    }

    static boolean isValidName(String name ){
        boolean Valid_name_boolen = false;
        if(name.length()>0 && !name.equals("In-Valid Name")){
            String[] words = name.split(" ");
            for (String word : words) {
                char[] chars = word.toCharArray();
                for (char aChar : chars) {
                    if (aChar >= 65 && aChar <= 90 || aChar >= 97 && aChar <= 122) {
                        Valid_name_boolen = true;
                    } else {
                        Valid_name_boolen = false;
                    }
                }
            }
        }
       return  Valid_name_boolen;
    }

    static boolean IsValidEmail(String email){
        boolean isValidemail=true;
        if(email.length()==0)
            {
                isValidemail=true;
            }
            else
            {
                if(email.contains(" ")){
                    isValidemail = true;
                } else {
                    int count_SC=0;
                    char cha[] = email.toCharArray();
                    for(int i=0;i<cha.length;i++)
                    {
                        if((cha[i]>31 && cha[i]<48)||(cha[i]>57 && cha[i]<65)||(cha[i]>90 && cha[i]<97)||(cha[i]>122 && cha[i]<127))
                        {
                            count_SC++;
                        }
                    }
                    if(count_SC>5)
                    {
                        isValidemail=true;
                    }
                    else
                    {
                        String parts[] = email.split("@");
                        if(parts.length!=2)
                        {
                            isValidemail=true;
                        }
                        else
                        {
                            String userName = parts[0];
                            if(!(userName.length()>=1))
                            {
                                isValidemail = true;
                            }else{
                                String domainName = parts[1];
                                if (domainName.charAt(0)=='.'){
                                    isValidemail=true;


                                }
                                char ch[] = domainName.toCharArray();
                                int c=0;
                                for(int i=0;i<ch.length;i++)
                                {
                                    if(ch[i]=='.')
                                        c++;
                                }
                                if(c>2)
                                {
                                    isValidemail=true;
                                }
                                else
                                {
                                    boolean res = (domainName.contains(".com")||domainName.contains(".co"));
                                    if(!res)
                                    {
                                        isValidemail=true;
                                    }
                                    else
                                    {
                                        isValidemail=false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return isValidemail;
    }

    static boolean isValidFee(String fee){
        boolean validFee=true;
        if (Integer.parseInt(fee) <= 999) {
            return false;
        }
        for (int i = 0; i < fee.length(); i++) {
            if (!Character.isDigit(fee.charAt(i))) {
                return false;
            }
        }
        return validFee;
    }
    static boolean isValidmedicinePrice(String fee){
        boolean validFee=true;
        if (Integer.parseInt(fee) <= 0) {
            return false;
        }
        for (int i = 0; i < fee.length(); i++) {
            if (!Character.isDigit(fee.charAt(i))) {
                return false;
            }
        }
        return validFee;    
    }
    
    static boolean isEligible(String age){
        boolean isEligible = false;
        if(Integer.parseInt(age)>=18 && Integer.parseInt(age)<=100){
            isEligible = true;
        }
        else{
            isEligible = false;
        }
        return isEligible;
    }
    static boolean isValidQualification(String qualification){
        boolean isValid_Q = false;
         for (int i = 0; i < qualification.length(); i++) {
            char ch = qualification.charAt(i);
            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
               isValid_Q = false;
            }
            else{
                isValid_Q = true;
            }
         }
         return isValid_Q;
    }
    static boolean isValidAge(String age){
        boolean isEligible = false;
        if(Integer.parseInt(age)>=1 && Integer.parseInt(age)<=100){
            isEligible = true;
        }
        else{
            isEligible = false;
        }
        return isEligible;      
    }
}