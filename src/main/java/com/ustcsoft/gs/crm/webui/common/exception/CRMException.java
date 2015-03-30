package com.ustcsoft.gs.crm.webui.common.exception;

/**
 * CRMException
 * 
 * @author tangyunpeng
 * 
 */
public class CRMException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param msg
     */
    public CRMException(String msg) {

        super(msg);
    }

    /**
     * 
     * @param msg
     *            error message.
     * @param cause
     *            Exception
     */
    public CRMException(String msg, Throwable cause) {

        super(msg, cause);
    }

    /**
     * 
     * @param cause
     */
    public CRMException(Throwable cause) {

        super(cause);
    }
}