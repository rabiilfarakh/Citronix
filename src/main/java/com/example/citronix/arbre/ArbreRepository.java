package com.example.citronix.arbre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArbreRepository extends JpaRepository<Arbre,UUID> {
}
