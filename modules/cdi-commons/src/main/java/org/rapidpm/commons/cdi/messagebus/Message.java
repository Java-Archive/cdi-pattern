/*
 * Copyright [2013] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.rapidpm.commons.cdi.messagebus;

import javax.enterprise.util.AnnotationLiteral;

/**
 * User: Sven Ruppert
 * Date: 31.07.13
 * Time: 10:19
 * <p></p>
 * q : AnnotationLiteral from the modul, like a Scope f communication
 * t: type that is handled by the message
 */
public class Message<T> {

    private AnnotationLiteral annotationLiteral;

    private T value;


    public AnnotationLiteral getAnnotationLiteral() {
        return annotationLiteral;
    }

    public void setAnnotationLiteral(AnnotationLiteral annotationLiteral) {
        this.annotationLiteral = annotationLiteral;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("annotationLiteral=").append(annotationLiteral);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
