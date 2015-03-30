package com.ustcsoft.gs.crm.webui.common.exception;

/**
 * CRMDBException
 * 
 * @author tangyunpeng
 * 
 */
public class CRMDBException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param msg
     */
    public CRMDBException(String msg) {

        super(msg);
    }

    /**
     * 
     * @param msg
     *            error message.
     * @param cause
     *            Exception
     */
    public CRMDBException(String msg, Throwable cause) {

        super(msg, cause);
    }

    /**
     * 
     * @param cause
     */
    public CRMDBException(Throwable cause) {

        super(cause);
    }
}