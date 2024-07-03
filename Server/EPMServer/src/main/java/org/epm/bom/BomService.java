package org.epm.bom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BomService {

    private final BomRepository bomRepository;
}
