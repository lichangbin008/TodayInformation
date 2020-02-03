package com.web.god.apt;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.web.god.annotation.MvpEmptyViewFactory"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MvpProcessor extends AbstractProcessor {
    public Filer mFiler;
    @Override
    public boolean process(Set<? extends TypeElement> annotastions, RoundEnvironment roundEnv) {
        mFiler = processingEnv.getFiler();
        new MvpEmptyViewProcessor().process(roundEnv,this);
        return true;
    }
}
