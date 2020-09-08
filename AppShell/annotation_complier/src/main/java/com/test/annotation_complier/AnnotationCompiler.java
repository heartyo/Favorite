package com.test.annotation_complier;

import com.google.auto.service.AutoService;
import com.test.annotation.BindPath;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * 找到注解，生成代码
 * //@SupportedAnnotationTypes({"com.test.annotation.BindPath"})//以下重写相同
 */
@AutoService(Processor.class)//注册注解处理器
public class AnnotationCompiler extends AbstractProcessor {

    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();

    }

    //支持的java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    //支持的注解类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //方法节点 ExecutableElement
        //类节点 TypeElement
        //成员变量节点 VariableElement

        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(BindPath.class);

        HashMap<String, String> map = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            String name = typeElement.getQualifiedName().toString();
            String key = typeElement.getAnnotation(BindPath.class).value();
            map.put(key, name + ".class");
        }
        if (map.size() > 0) {
            String utilName = "ActivityUtil" + System.currentTimeMillis();
            Writer writer = null;
            try {
                JavaFileObject sourceFile = filer.createSourceFile("com.test.temp." + utilName);
                writer = sourceFile.openWriter();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("package com.test.temp;\n");
                stringBuffer.append("import com.test.router.IRouter;\n");
                stringBuffer.append("import com.test.router.Router;\n");
                stringBuffer.append("public class " + utilName + " implements IRouter {\n");
                stringBuffer.append("@Override\n");
                stringBuffer.append("public void putActivity() {\n");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String activityName = map.get(key);
                    stringBuffer.append("Router.getInstance().addActivity(\"" + key + "\"," + activityName + ");\n");
                }
                stringBuffer.append("}\n}");
                writer.write(stringBuffer.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}



