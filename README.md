# *Тестовое задание ШИФТ ЦФТ (Java)*
[Ссылка на файл с заданием](https://drive.google.com/file/d/10qhGjn7biURykP8hIcT6NJHoGX-WOHUh/view)

---

## Инструкция по запуску

Проект разработан с использованием Java 20 и системы сборки Maven 3.9.4. Сборка в Jar осуществляется с помощью Maven (плагин Assembly).
Использованные зависимости (см. *pom.xml*):
- Apache Commons CLI (1.5.0)
- SLF4J + SLF4J Simple (2.0.7)
- JUnit (4.13.2)

Соберите проект в исполняемый Jar с использованием Maven:

    mvn clean compile assembly:single

Исполняемый Jar расположен в директории target.
Параметры запуска:
- `-i`/`-s` - тип данных входного файла (*обязательный*)
- [`-a`/`-d`] - режим сортировки (возрастание/убывание) (*необязательный*, по умолчанию по возрастанию)
- `--input` - название входных файлов (не менее одного) (*обязательный*)
- `--output` - название выходных файлов (*обязательный*)

Примеры запуска:

    java -jar cft-sort.jar -i -a --input a.txt b.txt c.txt --output out.txt
    java -jar cft-sort.jar -s --input in1.txt in2.txt --output ok.txt

В случае неверного ввода высветится причина и подсказка вида:

    usage: -jar cft-sort.jar [-a | -d]  -i | -s --input <arg> --output <arg>
    -a                  Ascending order
    -d                  Descending order
    -i                  Integer type
        --input <arg>   Input files (at least 1)
        --output <arg>  Output file
    -s                  String type