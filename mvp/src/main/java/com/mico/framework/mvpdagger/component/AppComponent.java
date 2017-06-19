package com.mico.framework.mvpdagger.component;


import com.mico.framework.mvpdagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Singleton
@Component(modules = {
        AppModule.class})
public interface AppComponent {

}
