package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import org.opens.tanaguru.ruleimplementationloader.RuleImplementationLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author ADEX
 */
public class RuleImplementationLoaderServiceImpl implements
        RuleImplementationLoaderService {

    private DefiniteResultFactory definiteResultFactory;
    private IndefiniteResultFactory indefiniteResultFactory;
    private NomenclatureLoaderService nomenclatureLoaderService;
    private ProcessRemarkFactory processRemarkFactory;
    private RuleImplementationLoader ruleImplementationLoader;
    private SourceCodeRemarkFactory sourceCodeRemarkFactory;

    public RuleImplementationLoaderServiceImpl() {
        super();
    }

    public Set<RuleImplementation> loadRuleImplementationSet(Set<Test> testSet) {
        Set<RuleImplementation> ruleImplementationSet = new HashSet<RuleImplementation>();
        for (Test test : testSet) {
            ruleImplementationSet.add(loadRuleImplementation(test));
        }
        return ruleImplementationSet;
    }

    public void setDefiniteResultFactory(
            DefiniteResultFactory definiteResultFactory) {
        this.definiteResultFactory = definiteResultFactory;
    }

    public void setIndefiniteResultFactory(
            IndefiniteResultFactory indefiniteResultFactory) {
        this.indefiniteResultFactory = indefiniteResultFactory;
    }

    public void setNomenclatureLoaderService(
            NomenclatureLoaderService nomenclatureService) {
        this.nomenclatureLoaderService = nomenclatureService;
    }

    public void setProcessRemarkFactory(
            ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setRuleImplementationLoader(
            RuleImplementationLoader ruleImplementationLoader) {
        this.ruleImplementationLoader = ruleImplementationLoader;
    }

    public void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }

    public RuleImplementation loadRuleImplementation(Test test) {
        ruleImplementationLoader.setArchiveName(test.getRuleArchiveName());
        ruleImplementationLoader.setClassName(test.getRuleClassName());
        ruleImplementationLoader.run();

        RuleImplementation ruleImplementation = ruleImplementationLoader.getResult();
        ruleImplementation.setTest(test);
        ruleImplementation.setDefiniteResultFactory(definiteResultFactory);
        ruleImplementation.setIndefiniteResultFactory(indefiniteResultFactory);
        ruleImplementation.setProcessRemarkFactory(processRemarkFactory);
        ruleImplementation.setSourceCodeRemarkFactory(sourceCodeRemarkFactory);
        ruleImplementation.setNomenclatureLoaderService(nomenclatureLoaderService);

        return ruleImplementation;
    }
}
