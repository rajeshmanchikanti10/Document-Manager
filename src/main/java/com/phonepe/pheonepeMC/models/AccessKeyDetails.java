package com.phonepe.pheonepeMC.models;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AccessKeyDetails {
    private String accessKey;
    private Instant ttl;
}
