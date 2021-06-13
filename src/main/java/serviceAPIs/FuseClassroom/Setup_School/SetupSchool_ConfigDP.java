package serviceAPIs.FuseClassroom.Setup_School;

import frameworkBase.TestBotServiceWrapper;
import org.testng.annotations.DataProvider;

public class SetupSchool_ConfigDP {

    @DataProvider(name = "addSchoolValidData")
    public Object[][] addSchoolValidData() {
        //String School_name,String Department_name
        return new Object[][]
                {
                        {
                                TestBotServiceWrapper.random_Text(),
                                "Information Science"
                        },
                        {
                                TestBotServiceWrapper.random_Text(),
                                "BBA"
                        },
                        {
                                TestBotServiceWrapper.random_Text(),
                                "MBBS"
                        }
                };
    }

    @DataProvider(name = "addSchoolInvalidData")
    public Object[][] addSchoolInvalidData() {
        //String School_name,String Department_name

        return new Object[][]
                {
                        {
                                TestBotServiceWrapper.random_Text(),
                                " ",
                                "School of Organization with this name already exists."
                        },
                        {
                                " ",
                                "Civil Engineering",
                                "Missing School Name"
                        },
                        {
                                " ",
                                " ",
                                "School of Organization with this name already exists."
                        },
                        {
                                "$$$",
                                "@@@",
                                "Invalid data for School/Department"
                        }
                };
    }

    @DataProvider(name = "multiDepartmentValidData")
    public Object[][] multiDepartmentValidData() {
        //String Department_name1,String Department_name2,String Department_name3,String School_name
        return new Object[][]
                {
                        {

                                "CIVIL",
                                "ELECTRICAL",
                                "MECHANICAL",
                                TestBotServiceWrapper.random_Text()
                        },
                        {
                                "Civil Engineering",
                                "Information Science",
                                "Mechatronics",
                                TestBotServiceWrapper.random_Text(),
                        },

                };
    }

    @DataProvider(name = "multiDepartmentInvalidData")
    public Object[][] multiDepartmentInvalidData() {
        //String Department_name1,String Department_name2,String Department_name3,String School_name
        return new Object[][]
                {
                        {

                                " ",
                                "ELECTRICAL",
                                "MECHANICAL",
                                TestBotServiceWrapper.random_Text()
                        },
                        {       "Civil Engineering",
                                " ",
                                "BBA",
                                TestBotServiceWrapper.random_Text(),

                        },
                        {       " ",
                                " ",
                                " ",
                                TestBotServiceWrapper.random_Text(),

                        },
                        {
                                "",
                                TestBotServiceWrapper.random_Text(),
                                TestBotServiceWrapper.random_Text(),
                                TestBotServiceWrapper.random_Text()
                        }

                };
    }

    @DataProvider(name = "modifyInvalidData")
    public Object[][] modifyInvalidData() {
        return new Object[][]
                {

                        {
                                "",
                                TestBotServiceWrapper.random_Text(),
                                TestBotServiceWrapper.random_Text(),
                                "[School of Organization is missing]"
                        },
                        {
                                TestBotServiceWrapper.random_Text(),
                                "",
                                TestBotServiceWrapper.random_Text(),
                                "Department name is missing"
                        },
                        {
                                TestBotServiceWrapper.random_Text(),
                                TestBotServiceWrapper.random_Text(),
                                "",
                                "Department name is missing"
                        },
                        {
                                "",
                                "",
                                "",
                                "[School of Organization is missing]"
                        }


                };

    }

    @DataProvider(name = "InvalidDepartmentID")
    public Object[][] InvalidDepartmentID()
    {
            return new Object[][]
                    {
                            {
                                "5fcf5dd8e31ad00042e63510",
                                    "This is the only department associated with school 'TQFMRUU', so it cannot be deleted"

                            }      ,
                            {
                                "    ",
                                    " "
                            },
                            {
                                "12345667",
                                    "Department not found"
                            }
                    };
    }

}
