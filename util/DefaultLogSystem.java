package nl.tdegroot.games.pixxel.util;

import java.util.Date;


import java.io.PrintStream;

public class DefaultLogSystem implements LogSystem {
    /**
     * The output stream for dumping the log out on
     */
    public static PrintStream out = System.out;
    public static PrintStream err = System.err;

    /**
     * Log an error
     *
     * @param message The message describing the error
     * @param e       The exception causing the error
     */
    public void error(String message, Throwable e) {
        error(message);
        error(e);
    }

    /**
     * Log an error
     *
     * @param e The exception causing the error
     */
    public void error(Throwable e) {
        err.println(new Date() + " ERROR:" + e.getMessage());
        e.printStackTrace(err);
    }

    /**
     * Log an error
     *
     * @param message The message describing the error
     */
    public void error(String message) {
        err.println(new Date() + " ERROR:" + message);
    }

    /**
     * Log a warning
     *
     * @param message The message describing the warning
     */
    public void warn(String message) {
        out.println(new Date() + " WARN:" + message);
    }

    /**
     * Log an information message
     *
     * @param message The message describing the infomation
     */
    public void info(String message, boolean endLine) {
        out.print(new Date() + " INFO:" + message);
        if (endLine) out.println();
    }

    /**
     * Log a debug message
     *
     * @param message The message describing the debug
     */
    public void debug(String message, boolean endLine) {
        out.print(new Date() + " DEBUG:" + message);
        if (endLine) out.println();
    }

    /**
     * Log a warning with an exception that caused it
     *
     * @param message The message describing the warning
     * @param e       The cause of the warning
     */
    public void warn(String message, Throwable e) {
        warn(message);
        e.printStackTrace(out);
    }

}