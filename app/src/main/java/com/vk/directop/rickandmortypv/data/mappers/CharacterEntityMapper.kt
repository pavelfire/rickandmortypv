package com.vk.directop.rickandmortypv.data.mappers

import com.vk.directop.rickandmortypv.data.entities.CharacterEntity
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.Location
import com.vk.directop.rickandmortypv.data.remote.dto.character.Origin

class CharacterEntityMapper {
    fun toCharacterEntity(characterDTO: CharacterDTO): CharacterEntity {
        return CharacterEntity(
            created = characterDTO.created,
            episode = characterDTO.episode.toString(),
            gender = characterDTO.gender,
            id = characterDTO.id,
            image = characterDTO.image,
            location = characterDTO.location.toString(),
            name = characterDTO.name,
            origin = characterDTO.origin.toString(),
            species = characterDTO.species,
            status = characterDTO.status,
            type = characterDTO.type,
            url = characterDTO.url
        )
    }

    fun toCharacterDTO(characterEntity: CharacterEntity): CharacterDTO {
        return CharacterDTO(
            created = characterEntity.created,
            episode = characterEntity.episode.lines(),
            gender = characterEntity.gender,
            id = characterEntity.id,
            image = characterEntity.image,
            location = Location("characterEntity.location.lines()", ""),
            name = characterEntity.name,
            origin = Origin("characterEntity.origin.lines()", ""),
            species = characterEntity.species,
            status = characterEntity.status,
            type = characterEntity.type,
            url = characterEntity.url
        )
    }
}