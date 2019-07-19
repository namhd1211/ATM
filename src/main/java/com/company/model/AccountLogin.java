package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountLogin {
    private String accountNumber;
    private String pin;
}
