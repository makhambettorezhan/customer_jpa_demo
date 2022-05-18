package kz.jusan.customerjpa.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public List<Customer> getStudents() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> studentByEmail = customerRepository.findStudentByEmail(customer.getEmail());
        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        customerRepository.save(customer);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = customerRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("student with that id does not exist");
        }
        customerRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Customer student = customerRepository.findById(studentId)
                .orElseThrow(() ->
                        new IllegalStateException("student with id " + studentId + " does not exist")
                );

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Customer> studentOptional = customerRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
