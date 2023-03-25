package com.vk.directop.rickandmortypv.data.mappers

import com.vk.directop.rickandmortypv.data.entities.CharacterEntity
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO

class CharacterEntityMapper {
    fun toCharacterEntity(characterDTO: CharacterDTO): CharacterEntity {
        return CharacterEntity(
            created = characterDTO.created,
            episode = characterDTO.episode,
            gender = characterDTO.gender,
            id = characterDTO.id,
            image = characterDTO.image,
            location = characterDTO.location,
            name = characterDTO.name,
            origin = characterDTO.origin,
            species = characterDTO.species,
            status = characterDTO.status,
            type = characterDTO.type,
            url = characterDTO.url
        )
    }

    fun toCharacterDTO(characterEntity: CharacterEntity): CharacterDTO {
        return CharacterDTO(
            created = characterEntity.created,
            episode = characterEntity.episode,
            gender = characterEntity.gender,
            id = characterEntity.id,
            image = characterEntity.image,
            location = characterEntity.location,
            name = characterEntity.name,
            origin = characterEntity.origin,
            species = characterEntity.species,
            status = characterEntity.status,
            type = characterEntity.type,
            url = characterEntity.url
        )
    }
}