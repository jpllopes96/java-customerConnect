package tech.jplopes.customerconnect.controller.dto;

public record CreateCustomerDto(String fullName, String cpf, String email, String phoneNumber) {
}
