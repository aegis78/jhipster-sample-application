package com.aegis.board;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.aegis.board");

        noClasses()
            .that()
            .resideInAnyPackage("com.aegis.board.service..")
            .or()
            .resideInAnyPackage("com.aegis.board.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.aegis.board.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
