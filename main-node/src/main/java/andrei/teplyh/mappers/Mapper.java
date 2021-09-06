package andrei.teplyh.mappers;

public interface Mapper<E, D> {
    E dtoToEntity(D dto);

    D entityToDto(E entity);
}
