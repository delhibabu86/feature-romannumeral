package com.convert.romannumeral.model;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.openpojo.validation.utils.ValidationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * standard getter and setter test for code coverage
 *
 * @author dvengambhanumoorthy
 */
class ModelPackageTest {

    private List<PojoClass> pojoClasses;

    @BeforeEach
    public void _setup() {
        this.pojoClasses = PojoClassFactory.getPojoClasses(this.getClass().getPackage().getName(),
                new FilterPackageInfo());
    }

    @Test
    void testGetterAndSetter() {
        Validator validator = ValidatorBuilder.create()
                // Add Testers to validate behaviour for POJO_PACKAGE
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        for (final PojoClass pojoClass : this.pojoClasses) {
            validator.validate(pojoClass);
            if (!pojoClass.isEnum() && !pojoClass.isInterface()) {
                ValidationHelper.getMostCompleteInstance(pojoClass).toString();
            }
        }
    }
}
