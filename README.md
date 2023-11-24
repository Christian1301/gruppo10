# Descrizione Architettura di Progetto

1. RuleController:
   - Attributes:
     - `rules: List<Rule>`
     - `triggers: List<Trigger>`
     - `actions: List<Action>`
   - Methods:
     - `addRule(rule: Rule)`
     - `deleteRule(rule: Rule)`
     - `addTrigger(rule: Trigger)`
     - `deleteTrigger(rule: Trigger)`
     - `addAction(rule: Action)`
     - `deleteAction(rule: Action)`

2. Rule:
   - Attributes:
     - `trigger: Trigger`
     - `action: Action`
     - `oneTimeFiring: boolean`
     - `sleepingPeriod: Duration`
     - `isActive: boolean`
   - Methods:
     - `activateRule(rule: Rule)`
     - `deactivateRule(rule: Rule)`
     - `isTriggered(): boolean`
     - `executeAction()`

3. Trigger:(interface)
   - Subclasses: (considering various trigger types)
     - `TimeOfDayTrigger`
     - `DayOfWeekTrigger`
     - `DayOfMonthTrigger`
     - `DateTrigger`
     - `FileExistenceTrigger`
     - `FileSizeTrigger`
     - `ExternalProgramTrigger`
     - `LogicalNOTTrigger`
     - `LogicalANDTrigger`
     - `LogicalORTrigger`
   - Methods:
     - `evaluate(): boolean`

4. Action:(interface)
   - Subclasses: (considering various action types)
     - `WriteStringAction`
     - `CopyFileAction`
     - `MoveFileAction`
     - `DeleteFileAction`
     - `ExecuteExternalProgramAction`
     - `SequenceAction`
   - Methods:
     - `execute()`

5. FileManager:
   - Methods:
     - `saveRules(rules: List<Rule>, filePath: String)`
     - `loadRules(filePath: String): List<Rule>`

6. Scheduler:
   - Attributes:
     - `ruleController: RuleController`
     - `interval: Duration`
   - Methods:
     - `start()`
     - `stop()`
     - `checkRules()`

7. Counter:
   - Attributes:
     - `name: String`
     - `value: int`
   - Methods:
     - `setValue(newValue: int)`
     - `getValue(): int`

8. VariableSubstitution:
    - Methods:
      - `substituteVariables(inputString: String): String`


![VPR1KkCm38RlVGeVqs7x0Xc6WQ5m0ifG1iucKLCFhjsrdMxPxhxxrbPYb4RTMwnFagM_ijrh7wGBxKPF9fMMtekNLiEjDS5PhS69mqI8cN17eo_5W_BXCZfS9Mjmgcd0zU0LbyXa5PGrFRf9gqWkXAphcAA8ESkqtJJPQz0GO8mmf4lUeMv50rc778u5T10ND9Z5Vwaaua1RpXkGNll0PB86](https://github.com/Christian1301/gruppo10/assets/76565091/7a8495e8-0aae-4663-81d1-2cf9102a4dfa)

Le interfacce dei trigger e delle azioni sono concepite con due metodi distinti, "valuta" ed "esegui", ai quali fanno riferimento le classi corrispondenti. Ciò facilita l'aggiunta agevole di nuovi trigger e azioni.

Una regola è costituita da diverse componenti, tra cui il trigger, l'azione, la durata di sleep, un indicatore di attivazione e un indicatore di attivazione singola. La classe implementa metodi dedicati per attivare e disattivare la regola, eseguire l'azione e verificare lo stato di attivazione.

Il controllore delle regole è invece costituito da tre liste che contengono le regole, azioni e trigger corrispondenti, integrate con metodi appropriati per l'aggiunta e l'eliminazione di elementi da ciascuna lista.

-FileManager: Gestisce il salvataggio e il caricamento di regole in un file specifico attraverso i metodi saveRules e loadRules.

-Scheduler: Controlla le regole ad intervalli definiti con attributi come ruleController (controllore delle regole) e interval (intervallo di tempo). Metodi chiave includono start, stop, e checkRules.

-Counter: Rappresenta un contatore con attributi name (nome) e value (valore), offrendo i metodi setValue e getValue.

-VariableSubstitution: Fornisce il metodo substituteVariables per sostituire variabili in una stringa di input.



