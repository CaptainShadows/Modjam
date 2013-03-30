package cooler.exeptions;

import cooler.utils.Registry;

public class NotMyFault extends RuntimeException {

    /**
     * This Exception is not MY Fault
     */
    private static final long serialVersionUID = 263250481020341795L;

    private String mError;

    public NotMyFault(String var1) {
        this.mError = var1;
    }

    public String toString() {
        return "The "
                + Registry.name
                + " has a Problem.\nIT'S NOT MY FAULT! Below is how to fix it.\n"
                + this.mError
                + "\nDO NOT COME TO ME WITH THIS. YOU CAUSED IT YOURSELF, AND I TOLD YOU HOW TO FIX IT!";
    }
}
