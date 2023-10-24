package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    private static void task1() throws IOException {
        AtomicInteger i = new AtomicInteger();
        AtomicInteger j = new AtomicInteger(2);

        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter((integer) -> integer.getAge() >= 10 && integer.getAge() < 20)
                .sorted(Comparator.comparing(s->s.getAge()))
                .peek(animal -> {
                    System.out.println("ZOO №"+i +" "+ animal);
                    boolean b = j.weakCompareAndSet(8, 1);
                    if (b){
                        i.incrementAndGet();
                    }
                    j.incrementAndGet();
                })
                .collect(Collectors.toList());
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(i -> i.getOrigin().equals("Japanese")&& i.getGender().equals("Female"))
                .peek(animal -> animal.setBread(" UPPER_CASE"))
                .forEach(animal -> System.out.println(animal.getBread().toString()));
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30 && animal.getOrigin().startsWith("A"))
                .map(Animal::getOrigin)
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .map(Animal::getGender)
                .filter(animal -> animal.equals("Female"))
                .count();
        System.out.println("count = " + count);

    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter((animal) -> animal.getAge() >= 20 && animal.getAge() < 30
                        && animal.getOrigin().equals("Hungarian"))
                .count();
        System.out.println("count = " + count);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .takeWhile((animal) -> animal.getGender().equals("Male") && animal.getGender().equals("Female"))
                .count();
        System.out.println("Other Gender = " + count);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .takeWhile(animal -> animal.getOrigin().equals("Oceania"))
                .count();
        System.out.println("The Number Of Animals That Have a Country of Origin Oceania = " + count);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        Optional<Integer> max = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .map(Animal::getAge)
                .max(Integer::compare);
        System.out.println("Age of The Oldest Animal= " + max);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        Optional<char[]> min = animals.stream()
                .map((animal) -> animal.getBread().toCharArray())
                .min(Comparator.comparing(chars -> chars.length));
        System.out.println("Shortest Array = " + min.get().length);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int sum = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println("Total Age of All Animals = " + sum);


    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        OptionalDouble indonesian = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average();
        System.out.println("Average Age of All Animals From Indonesia = " + indonesian);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter((person) -> person.getGender().equals("Male")
                        && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >=18
                        && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <27
                        && person.getRecruitmentGroup()<=3)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        Map<String, List<Person>> map = new HashMap<>();
        map.put("Hospital",  houses.stream().filter(house -> house.getBuildingType().equals("Hospital"))
                .map(House::getPersonList).flatMap(Collection::stream).collect(Collectors.toList()));
        map.put("Civil building",  houses.stream()
                .map(House::getPersonList)
                .flatMap(Collection::stream)
                .filter((person) ->
                        Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >18 &&
                                Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <60)
                .limit(460)
                .collect(Collectors.toList()));
        System.out.println("map = " + map);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        List <Car>carList = new CopyOnWriteArrayList(cars);
        carList.stream()
                .filter((car) -> car.getCarMake().equals("Jaguar") ||
                        car.getColor().equals("White"))
                .peek(carLists -> {
                    double v = carLists.getMass() * 7.14;
                    System.out.println("Туркменистан " + carLists+ " Стоимость растаможки" + v);
                })
                .map(carList::remove)
                .collect(Collectors.toList());

        carList.stream()
                .filter((car) -> car.getMass() < 1500 ||
                        car.getCarMake().equals("BMW") &&
                                car.getCarMake().equals("Lexus") &&
                                car.getCarMake().equals("Chrysler") &&
                                car.getCarMake().equals("Toyota"))
                .peek(carLists -> {
                    double v = carLists.getMass() * 7.14;
                    System.out.println("Узбекистан " + carLists + " Стоимость растаможки" + v);
                })
                .map(carList::remove)
                .collect(Collectors.toList());
        carList.stream()
                .filter((car) -> car.getColor().equals("Black") &&
                        car.getMass() > 4000 ||
                        car.getCarMake().equals("GMC") ||
                        car.getCarMake().equals("Dodge"))
                .peek(carLists -> {
                    double v = carLists.getMass() * 7.14;
                    System.out.println("Казахстан " + carLists + " Стоимость растаможки" + v);
                })
                .map(carList::remove)
                .collect(Collectors.toList());
        carList.stream()
                .filter((car) -> car.getReleaseYear() < 1982 ||
                        car.getCarMake().equals("Civic") &&
                                car.getCarMake().equals("Cherokee"))
                .peek(carLists -> {
                    double v = carLists.getMass() * 7.14;
                    System.out.println("Кыргызстан " + carLists + " Стоимость растаможки" + v);
                })
                .map(carList::remove)
                .collect(Collectors.toList());
        carList.stream()
                .filter((car) -> car.getPrice() > 40000 &&
                        !Objects.equals(car.getColor(), "Yellow") &&
                        !Objects.equals(car.getColor(), "Red") &&
                        !Objects.equals(car.getColor(), "Green") &&
                        !Objects.equals(car.getColor(), "Blue"))
                .peek(carLists -> {
                    double v = carLists.getMass() * 7.14;
                    System.out.println("Россия " + carLists + " Стоимость растаможки" + v);
                })
                .map(carList::remove)
                .collect(Collectors.toList());
        carList.stream()
                .filter((car) -> car.getVin().contains("59"))
                .peek(carLists -> {
                    double v = carLists.getMass() * 7.14;
                    System.out.println("Монголия " + carLists + " Стоимость растаможки" + v);
                })
                .map(carList::remove)
                .collect(Collectors.toList());
    }

    private static void task15() throws IOException {
        List<Double> sumList = new ArrayList<>();
        List<Flower> flowers = Util.getFlowers();
        flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                .sorted(Comparator.comparingInt(Flower::getPrice))
                .sorted(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed())
                .filter(flower -> flower.getCommonName().matches("^[C-S].*"))
                .filter(Flower::isShadePreferred)
                .filter((flower) -> flower.getFlowerVaseMaterial().contains("Aluminum")&&
                        flower.getFlowerVaseMaterial().contains("Glass")&&
                        flower.getFlowerVaseMaterial().contains("Steel"))
                .peek(flower -> {
                    double sum = flower.getWaterConsumptionPerDay() * 1826 * 1.39;
                    double allSum = flower.getPrice() + sum;
                    System.out.println("Затраты на цветок: " +flower +" = "+ allSum+"$");
                    sumList.add(allSum);
                })
                .collect(Collectors.toList());
        System.out.println("Общая сумма обслуживания всех растений = " + sumList.stream().mapToDouble(Double::shortValue).sum());
    }


    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge()<18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(student -> System.out.println(student.getSurname() + " - " + student.getAge()));
    }
    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        Map<Integer, Integer> studentAgeMap = students.stream()
                .collect(Collectors.toMap(Student::getId, Student::getAge));
        Map<Integer, String> studentFacultyMap = students.stream()
                .collect(Collectors.toMap(Student::getId, Student::getFaculty));
        Map<String, Double> facultyAverageAgeMap = examinations.stream()
                .collect(Collectors.groupingBy(e -> studentFacultyMap.get(e.getStudentId()),
                        Collectors.averagingInt(e -> studentAgeMap.get(e.getStudentId()))));
        facultyAverageAgeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        List<Student> studentsWithPassedExam3 = students.stream()
                .filter(student -> examinations.stream()
                        .filter(exam -> exam.getStudentId() == student.getId() && exam.getExam3() > 4)
                        .findAny()
                        .isPresent())
                .collect(Collectors.toList());
        studentsWithPassedExam3.forEach(student -> System.out.println(student.getSurname() + " " + student.getGroup()));
    }


    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        Map<String, Double> facultyAverageExam1Score = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.averagingDouble(student ->
                                examinations.stream()
                                        .filter(exam -> exam.getStudentId() == student.getId())
                                        .mapToDouble(Examination::getExam1)
                                        .average()
                                        .orElse(0.0)
                        )));

        Optional<Map.Entry<String, Double>> maxFacultyEntry = facultyAverageExam1Score.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (maxFacultyEntry.isPresent()) {
            System.out.println("Факультет с максимальной средней оценкой по первому экзамену: " + maxFacultyEntry.get().getKey());
        } else {
            System.out.println("Нет данных о факультетах или оценках по первому экзамену.");
        }
    }


    public static void task21() {
        List<Student> students = Util.getStudents();
        Map<String, Long> groupStudentCount = students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()));
        groupStudentCount.forEach((group, count) ->
                System.out.println("Count " + group + ": " + count + " Student"));

    }

    public static void task22() {
        List<Student> students = Util.getStudents();

        Map<String, Integer> facultyMinAge = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparing(Student::getAge)),
                                minStudentOpt -> minStudentOpt.map(Student::getAge).orElse(0))));

        facultyMinAge.forEach((faculty, minAge) ->
                System.out.println("Faculty " + faculty + ": min Age - " + minAge + " Age"));
    }
}
