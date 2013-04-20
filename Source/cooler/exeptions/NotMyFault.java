package cooler.exeptions;

import cooler.utils.Archive;

public class NotMyFault extends RuntimeException {

    /**
     * This Exception is not MY Fault
     */
    private static final long serialVersionUID = 263250481020341795L;

    private String mError;

    public NotMyFault(String var1) {
        mError = var1;
    }

    @Override
    public String toString() {
        return "The "
                + Archive.name
                + " has a Problem.\nIT'S NOT MY FAULT! Below is how to fix it.\n"
                + mError
                + "\nDO NOT COME TO ME WITH THIS. YOU CAUSED IT YOURSELF, AND I TOLD YOU HOW TO FIX IT!";
    }
}
