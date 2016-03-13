package org.learning.instrumentation;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class MyTransformer implements ClassFileTransformer{

	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		byte [] byteCode = classfileBuffer;
		if(className.equals("org/learning/instrumentation/DummyClass")){
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("org.learning.instrumentation.DummyClass");
				CtMethod m = cc.getDeclaredMethod("randomSleep");
				m.addLocalVariable("elapsedTime", CtClass.longType);
				m.insertBefore("elapsedTime = System.currentTimeMillis();");
				m.insertAfter("{ elapsedTime = System.currentTimeMillis() - elapsedTime; "
						+"System.out.println(\"Method Executed in ms: \" +elapsedTime); }");
				byteCode = cc.toBytecode();
				cc.detach();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return byteCode;
	}

}
