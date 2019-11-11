package org.xwalk.core.internal;

class ErrorCodeConversionHelper {
    ErrorCodeConversionHelper() {
        super();
    }

    static int convertErrorCode(int arg0) {
        switch(arg0) {
            case -339: {
                return -3;
            }
            case -338: {
                return -4;
            }
        }

        switch(arg0) {
            case -302: 
            case -301: {
                return -10;
            }
            case -300: {
                return -12;
            }
        }

        switch(arg0) {
            case -208: 
            case -207: 
            case -206: 
            case -205: 
            case -204: 
            case -203: 
            case -202: 
            case -201: 
            case -200: {
                return 0;
            }
            default: {
                switch(arg0) {
                    case -135: 
                    case -134: {
                        return -11;
                    }
                    default: {
                        switch(arg0) {
                            case -130: {
                                return -5;
                            }
                            case -129: {
                                return -11;
                            }
                            default: {
                                switch(arg0) {
                                    case -127: {
                                        return -5;
                                    }
                                    case -126: 
                                    case -125: {
                                        return -11;
                                    }
                                    default: {
                                        switch(arg0) {
                                            case -119: {
                                                return -15;
                                            }
                                            case -118: {
                                                return -8;
                                            }
                                            case -115: {
                                                return -5;
                                            }
                                            case -117: 
                                            case -116: 
                                            case -114: 
                                            case -113: 
                                            case -112: 
                                            case -111: 
                                            case -110: 
                                            case -107: {
                                                return -11;
                                            }
                                            case -109: 
                                            case -108: 
                                            case -106: 
                                            case -105: {
                                                return -2;
                                            }
                                            case -104: 
                                            case -103: 
                                            case -102: 
                                            case -101: 
                                            case -100: {
                                                return -6;
                                            }
                                            default: {
                                                switch(arg0) {
                                                    case -15: {
                                                        return -6;
                                                    }
                                                    case -14: {
                                                        return -14;
                                                    }
                                                    case -13: 
                                                    case -12: {
                                                        return -15;
                                                    }
                                                    default: {
                                                        switch(arg0) {
                                                            case -8: {
                                                                return -13;
                                                            }
                                                            case -7: {
                                                                return -8;
                                                            }
                                                            default: {
                                                                switch(arg0) {
                                                                    case -343: 
                                                                    case -341: {
                                                                        return -4;
                                                                    }
                                                                    case -323: {
                                                                        return -5;
                                                                    }
                                                                    case -310: {
                                                                        return -9;
                                                                    }
                                                                    case -210: {
                                                                        return 0;
                                                                    }
                                                                    case -166: 
                                                                    case -137: {
                                                                        return -2;
                                                                    }
                                                                    case -123: {
                                                                        return -11;
                                                                    }
                                                                    case -22: {
                                                                        return -6;
                                                                    }
                                                                    case -331: 
                                                                    case -1: {
                                                                        return -7;
                                                                    }
                                                                    default: {
                                                                        return -1;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return -4;
    }
}

