package com.nikoskatsanos.jutils.core;

/**
 * <p>Class which holds a variable. Useful with lamdas or anonymous classes, where the variables to operate on need to
 * be final or effectively final</p>
 * <pre>
 *     {@code
 *     final VarHolder< Integer > intVal = new VarHolder<>( 1 );
 *     final Runnable r = () -> intVal.setValue( intVal.getValue() + 1 );
 *     }
 * </pre>
 *
 * @author nikkatsa
 */
public class VarHolder<T> {

    private volatile T under;

    public VarHolder() {
    }

    public VarHolder(T under) {
        this.under = under;
    }

    public T getValue() {
        return this.under;
    }

    public void setValue(T under) {
        this.under = under;
    }
}
