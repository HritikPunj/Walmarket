# Code Standards

    /**  
    * Add a class description above class definition, no header  
    * required since file name is taked into markdown  
    */  
    public class UpperCaseClass
    {  
        static final int CONSTANT_ALL_CAPS;

        private double camelCaseInstanceVars;

        /**
        * #### UpperCaseClass()
        * Constructor Description
        * (ie. functionality, parameter meaning, etc.)
        */
        public UpperCaseClass() 
        {
            //Normal comments valid and will be excluded from Docs
            /*
            * Even block comments
            * are fine within code
            */
            int localVarsCamelCase = 0;
        }

        /**
        * #### boolean camelCaseMethod(int)
        * Method desc. with simplified parameters (no names)
        */
        public boolean camelCaseMethod(int camelCaseParams) 
        {
            double localVarsCamelCase = 0;
            return true;
        }
    }

**Warning**: Lines such as /* * * * \*/ and others which result in /*\* appearing as a subtring once spaces are removed are problems and will break the Documentation generation.
