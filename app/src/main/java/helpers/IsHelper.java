package helpers;

import java.util.Date;

/**
 * Created by joao.silva.
 */

public class IsHelper {

    protected It is(String string) {
        return new It(string);
    }

    protected It is(int number) {
        return new It(number);
    }

    protected It is(short number) {
        return new It(number);
    }

    protected It is(long number) {
        return new It(number);
    }

    protected It is(float number) {
        return new It(number);
    }

    protected It is(double number) {
        return new It(number);
    }

    protected boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    protected boolean isNull(Object obj) {
        return obj == null;
    }

    protected boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    protected boolean isNullOrFromTheFuture(Date date) {
        return isNull(date) || date.after(new Date());
    }

    protected boolean isPositive(Short number) {
        return number>0;
    }

    protected boolean isPositive(Integer number) {
        return number>0;
    }

    protected boolean isPositive(Long number) {
        return number>0;
    }

    public class It
    {
        private String string;
        private int integer;
        private short shortV;
        private long longV;
        private float floatV;
        private double doubleV;
        private short type;

        public It(String number) {
            this.string = number;
            this.type = 0;
        }

        public It(int integer) {
            this.integer = integer;
            this.type = 1;
        }

        public It(short shortV) {
            this.shortV = shortV;
            this.type = 2;
        }

        public It(long longV) {
            this.longV = longV;
            this.type = 3;
        }

        public It(float floatV) {
            this.floatV = floatV;
            this.type = 4;
        }

        public It(double doubleV) {
            this.doubleV = doubleV;
            this.type = 5;
        }

        public boolean equal(double equalNumber) {
            if (this.type == 0) {
                return this.string.length() == equalNumber;
            } else if (this.type == 1) {
                return this.integer == equalNumber;
            } else if (this.type == 2) {
                return this.shortV == equalNumber;
            } else if (this.type == 3) {
                return this.longV == equalNumber;
            } else if (this.type == 4) {
                return this.floatV == equalNumber;
            } else if (this.type == 5) {
                return this.doubleV == equalNumber;
            }

            return false;
        }

        public boolean notEqual(double notEqualNumber) {
            if (this.type == 0) {
                return this.string.length() != notEqualNumber;
            } else if (this.type == 1) {
                return this.integer != notEqualNumber;
            } else if (this.type == 2) {
                return this.shortV != notEqualNumber;
            } else if (this.type == 3) {
                return this.longV != notEqualNumber;
            } else if (this.type == 4) {
                return this.floatV != notEqualNumber;
            } else if (this.type == 5) {
                return this.doubleV != notEqualNumber;
            }

            return false;
        }

        public boolean smallerThan(double biggerNumber) {
            if (this.type == 0) {
                return this.string.length() < biggerNumber;
            } else if (this.type == 1) {
                return this.integer < biggerNumber;
            } else if (this.type == 2) {
                return this.shortV < biggerNumber;
            } else if (this.type == 3) {
                return this.longV < biggerNumber;
            } else if (this.type == 4) {
                return this.floatV < biggerNumber;
            } else if (this.type == 5) {
                return this.doubleV < biggerNumber;
            }

            return false;
        }

        public boolean smallerOrEqual(double number) {
            if (this.type == 0) {
                return this.string.length() <= number;
            } else if (this.type == 1) {
                return this.integer <= number;
            } else if (this.type == 2) {
                return this.shortV <= number;
            } else if (this.type == 3) {
                return this.longV <= number;
            } else if (this.type == 4) {
                return this.floatV <= number;
            } else if (this.type == 5) {
                return this.doubleV <= number;
            }

            return false;
        }

        public Or orSmallerThan(double biggerNumber) {
            if (this.type == 0) {
                if (this.string.length() < biggerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 1) {
                if (this.integer < biggerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 2) {
                if (this.shortV < biggerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 3) {
                if (this.longV < biggerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 4) {
                if (this.floatV < biggerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 5) {
                if (this.doubleV < biggerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            }
            return new Or(this, null);
        }

        public boolean biggerThan(double smallerNumber) {
            if (this.type == 0) {
                return this.string.length() > smallerNumber;
            } else if (this.type == 1) {
                return this.integer > smallerNumber;
            } else if (this.type == 2) {
                return this.shortV > smallerNumber;
            } else if (this.type == 3) {
                return this.longV > smallerNumber;
            } else if (this.type == 4) {
                return this.floatV > smallerNumber;
            } else if (this.type == 5) {
                return this.doubleV > smallerNumber;
            }
            return false;
        }

        public boolean biggerOrEqual(double number) {
            if (this.type == 0) {
                return this.string.length() >= number;
            } else if (this.type == 1) {
                return this.integer >= number;
            } else if (this.type == 2) {
                return this.shortV >= number;
            } else if (this.type == 3) {
                return this.longV >= number;
            } else if (this.type == 4) {
                return this.floatV >= number;
            } else if (this.type == 5) {
                return this.doubleV >= number;
            }
            return false;
        }

        public Or orBiggerThan(double smallerNumber) {
            if (this.type == 0) {
                if (this.string.length() > smallerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 1) {
                if (this.integer > smallerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 2) {
                if (this.shortV > smallerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 3) {
                if (this.longV > smallerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 4) {
                if (this.floatV > smallerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            } else if (this.type == 5) {
                if (this.doubleV > smallerNumber) {
                    return new Or(this, true);
                }
                return new Or(this, false);
            }
            return new Or(this, null);
        }

        public And between(double smallerNumber) {
            if (this.type == 0) {
                if (this.string.length() > smallerNumber) {
                    return new And(this, true);
                }
            } else if (this.type == 1) {
                if (this.integer > smallerNumber) {
                    return new And(this, true);
                }
            } else if (this.type == 2) {
                if (this.shortV > smallerNumber) {
                    return new And(this, true);
                }
            } else if (this.type == 3) {
                if (this.longV > smallerNumber) {
                    return new And(this, true);
                }
            } else if (this.type == 4) {
                if (this.floatV > smallerNumber) {
                    return new And(this, true);
                }
            } else if (this.type == 5) {
                if (this.doubleV > smallerNumber) {
                    return new And(this, true);
                }
            }
            return new And(this, false);
        }

        public class And {

            private It it;
            private Short type;
            private boolean isTrue;

            public And(It it, Boolean isTrue) {
                this.it = it;
                this.isTrue = isTrue;
                this.type = it.type;
            }

            public boolean and(double biggerNumber) {
                if (isTrue) {
                    if (this.type == 0) {
                        return this.it.string.length() < biggerNumber;
                    } else if (this.type == 1) {
                        return this.it.integer < biggerNumber;
                    } else if (this.type == 2) {
                        return this.it.shortV < biggerNumber;
                    } else if (this.type == 3) {
                        return this.it.longV < biggerNumber;
                    } else if (this.type == 4) {
                        return this.it.floatV < biggerNumber;
                    } else if (this.type == 5) {
                        return this.it.doubleV < biggerNumber;
                    }
                }
                return false;
            }
        }

        public class Or {

            private It it;
            private Short type;
            private Boolean isTrue;

            public Or(It it, Boolean isTrue) {
                this.it = it;
                this.isTrue = isTrue;
                this.type = it.type;
            }

            public boolean orEqual(double equalNumber) {
                if (this.isTrue != null) {
                    if (this.isTrue) {
                        return true;
                    }
                    if (this.type == 0) {
                        return this.it.string.length() == equalNumber;
                    } else if (this.type == 1) {
                        return this.it.integer == equalNumber;
                    } else if (this.type == 2) {
                        return this.it.shortV == equalNumber;
                    } else if (this.type == 3) {
                        return this.it.longV == equalNumber;
                    } else if (this.type == 4) {
                        return this.it.floatV == equalNumber;
                    } else if (this.type == 5) {
                        return this.it.doubleV == equalNumber;
                    }
                }
                return false;
            }

            public boolean orBiggerThan(double smallerNumber) {
                if (this.isTrue != null) {
                    if (this.isTrue) {
                        return true;
                    }
                    if (this.type == 0) {
                        return this.it.string.length() > smallerNumber;
                    } else if (this.type == 1) {
                        return this.it.integer > smallerNumber;
                    } else if (this.type == 2) {
                        return this.it.shortV > smallerNumber;
                    } else if (this.type == 3) {
                        return this.it.longV > smallerNumber;
                    } else if (this.type == 4) {
                        return this.it.floatV > smallerNumber;
                    } else if (this.type == 5) {
                        return this.it.doubleV > smallerNumber;
                    }
                }
                return false;
            }

            public boolean orSmallerThan(double biggerNumber) {
                if (this.isTrue != null) {
                    if (this.isTrue) {
                        return true;
                    }
                    if (this.type == 0) {
                        return this.it.string.length() < biggerNumber;
                    } else if (this.type == 1) {
                        return this.it.integer < biggerNumber;
                    } else if (this.type == 2) {
                        return this.it.shortV < biggerNumber;
                    } else if (this.type == 3) {
                        return this.it.longV < biggerNumber;
                    } else if (this.type == 4) {
                        return this.it.floatV < biggerNumber;
                    } else if (this.type == 5) {
                        return this.it.doubleV < biggerNumber;
                    }
                }
                return false;
            }
        }
    }
}
