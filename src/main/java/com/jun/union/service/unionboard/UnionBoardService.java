package com.jun.union.service.unionboard;

import com.jun.union.define.enums.EResultCode;
import com.jun.union.dto.ResponseDataDTO;
import com.jun.union.dto.common.GeneratorIDTools;
import com.jun.union.service.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnionBoardService {

    private final UnionBoardRepository unionBoardRepository;

    /**
     *
     * @param dto
     * @return
     */
    public ResponseDataDTO<UnionBoardDTO> save(UnionBoardDTO dto) {
        UnionBoardEntity unionBoardEntity = new UnionBoardEntity();
        unionBoardEntity.setUnionBoardId(GeneratorIDTools.getId("UB"));
        unionBoardRepository.save(unionBoardEntity);
        return new ResponseDataDTO<>(EResultCode.SUCCESS, dto);
    }
}
