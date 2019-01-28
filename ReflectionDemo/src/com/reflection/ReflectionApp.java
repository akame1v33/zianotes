package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import com.annotation.Def;
import com.annotation.Defaultable;
import com.annotation.Defaultable.Default;
import com.listener.AddListener;

public class ReflectionApp implements AddListener{
	
	public static void main(String args[]) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		
		ReflectionApp app = new ReflectionApp();
		app.annotationProcess(Student.class, (String value) -> {
			System.out.println( value + " wew " );
			return value;
		} );

	}
	
	public void annotationProcess(Class<?> clazz, AddListener listener) throws NoSuchMethodException, SecurityException, NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
//		Class<Student> clazz = clazz;
		
		AddListener addListener = listener;
		Object object = clazz.newInstance();
		
		if( clazz.isAnnotationPresent( Defaultable.class ) ) {
			Defaultable defaultable =  clazz.getAnnotation(Defaultable.class);
//			System.out.println( defaultable.lastModified() );
//			System.out.println( defaultable.def() );
			Default def = defaultable.def();
			
			if( def == Default.ON ) {
//				
				
				Field[] fields = clazz.getDeclaredFields();
				
				for (Field field : fields) {
//					System.out.println("Declared Field : "+);
					if( field.isAnnotationPresent( Def.class ) ) {
						
						Def anDef = field.getAnnotation(Def.class);
						String value = anDef.value();
						String fieldName = field.getName();	
						Method m = clazz.getMethod("set"+fieldName.replaceFirst( fieldName.substring(0, 1) , fieldName.substring(0, 1).toUpperCase()), field.getType());
						System.out.println( m.getName() );
						value = (String) addListener.beforeAdd(value);
						if( field.getGenericType().toString().equalsIgnoreCase("int") ) {
							m.invoke(object,  Integer.parseInt(value) );
							
						} else {
							m.invoke(object,  value );
						}
					}
				}
				
				System.out.println( object.toString() );		
				
				
			}
		}
		
		
	}

	@Override
	public String beforeAdd(String value) {
		// TODO Auto-generated method stub
//		System.out.println("Before add listener: "+value);
		if( !Pattern.compile("\\s").matcher(value).matches() ) {
			value = value.toUpperCase();
		}
		return value;
	}
}
